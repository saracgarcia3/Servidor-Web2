package ServidorWeb;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiFunction;

public class HttpServer {

    // Map para rutas GET
    private static Map<String, BiFunction<Request, Response, String>> getRoutes = new HashMap<>();

    // Método estático para registrar rutas GET
    public static void get(String path, BiFunction<Request, Response, String> handler) {
        getRoutes.put(path, handler);
    }

    public static void main(String[] args) throws IOException {
        int port = 35008;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor corriendo en http://localhost:" + port);

            // Registrar rutas
            get("/hello", (req, res) -> "hello world!");
            get("/bye", (req, res) -> "goodbye!");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) return;

            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String path = tokens[1];

            Request req = new Request(method, path);
            Response res = new Response();

            if ("GET".equalsIgnoreCase(method)) {
                BiFunction<Request, Response, String> handler = getRoutes.get(path);
                if (handler != null) {
                    String result = handler.apply(req, res);
                    res.setBody(result);
                    sendResponse(out, res);
                    return;
                }
            }

            // Si no hay ruta registrada, intentar servir archivo estático
            serveStaticFile(path, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(OutputStream out, Response res) throws IOException {
        String httpResponse = "HTTP/1.1 " + res.getStatus() + " OK\r\n" +
                              "Content-Type: text/plain\r\n" +
                              "Content-Length: " + res.getBody().getBytes().length + "\r\n" +
                              "\r\n" +
                              res.getBody();
        out.write(httpResponse.getBytes(StandardCharsets.UTF_8));
        out.flush();
    }

    private static void serveStaticFile(String path, OutputStream out) throws IOException {
        // Carpeta de recursos
        File file = new File("resources" + path);
        if (!file.exists() || file.isDirectory()) {
            Response res = new Response();
            res.setStatus(404);
            res.setBody("404 Not Found");
            sendResponse(out, res);
            return;
        }

        byte[] content = Files.readAllBytes(file.toPath());
        String header = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: " + content.length + "\r\n" +
                        "\r\n";
        out.write(header.getBytes(StandardCharsets.UTF_8));
        out.write(content);
        out.flush();
    }

    // Clases internas para Request y Response
    public static class Request {
        private String method;
        private String path;

        public Request(String method, String path) {
            this.method = method;
            this.path = path;
        }

        public String getMethod() { return method; }
        public String getPath() { return path; }
    }

    public static class Response {
        private int status = 200;
        private String body = "";

        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }

        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
    }
}
