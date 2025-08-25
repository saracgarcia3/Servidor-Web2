<h1 align="center"> ​Servidor Web en Java💻​ </h1>
<p align="center">Proyecto que sirve archivos estáticos de tipo HTML, archivos java script, css e imagenes desde una carpeta "resources", incluye una interfaz web interactuar con archivos mediante un buscador y pruebas de servicios REST.</p>

## 📌 Parte 1
⚙️ ¿Cómo funciona?

### Servidor Web (HttpServer.java)
- Crea un `ServerSocket` escuchando en el puerto **35008**
- Recibe solicitudes HTTP y sirve archivos desde la carpeta **resources**
- Maneja contenido **Html, css, js e imágenes**
- Devuelve un mensaje **404** si el archivo no existe

### index.html
- Interfaz web para seleccionar tipo de archivo y ver los resultados
- Incluye botones para **buscar archivos** y probar **endpoints REST**

### style.css y script.js
- Proporcionan los estilos de la interfaz web
- `script.js` contiene funciones `buscar()` y `probarRest()` para interactuar con el servidor

### Carpeta resources/
- Contiene todos los archivos que el servidor puede servir (**Html, css, js, imágenes**)

### Imagen de la interfaz 

<p align="center">
  <img src="https://github.com/user-attachments/assets/dc63d535-8489-435d-a46d-7c0af431cef3" width="1022" height="578" />
</p>

## 📌 Parte 2 Microframeworks

1. Para la implementación de GET(), lo que hicimos fue usar un HashMap que guarda la URL, como valor va a tener la función handler que se ejecuta cuando se llega a esa ruta,  el método get va a registrar la nueva ruta y recibe como tal la URL y el lambda que seria el request ( información de la petición) y el response ( la respuesta que se enviara) y devuelve un String

<p align="center">
<img width="739" height="104" alt="image" src="https://github.com/user-attachments/assets/bdd72615-fa54-4dce-8e51-240f71d8818c" />
</p>

 De esta manera lo que queremos obtener es lo siguiente con lambda:

- **get("/hello", (req, res) -> "hello world!");**

<p align="center">
<img width="334" height="116" alt="image" src="https://github.com/user-attachments/assets/599a4528-068e-427a-be43-bb89de1c4f16" />
</p>
  
- **get("/bye",   (req, res) -> "goodbye!");**

<p align="center">
<img width="371" height="104" alt="image" src="https://github.com/user-attachments/assets/d6ebe7cb-3795-4989-b9a0-0fcc3e289bc9" />
</p>

2. Para este punto se implementó en la clase **Request**  un mecanismo que separa la ruta base (por ejemplo /hello) de la ruta completa con parámetros (/hello?name=Sara), de modo que los valores enviados en la URL se guardan en un mapa donde cada parámetro puede tener uno o varios valores. Con esto se pueden obtener fácilmente los datos desde el servicio REST usando métodos como getValue("name") para el primer valor o getValues("name") para todos, permitiendo que las respuestas sean según los parámetros de la petición

- La idea del ejericiio es obtener: **get("/hello", (req, res) -> "hello " + req.getValue("name"));**

<p align="center">
<img width="458" height="114" alt="image" src="https://github.com/user-attachments/assets/8cd2e893-7f67-4a60-8f75-24822456969a" />
</p>

<p align="center">
<img width="522" height="122" alt="image" src="https://github.com/user-attachments/assets/50940d02-005d-43b0-80a3-793c07187b68" />
</p>

3. En este punto se implementó el método **staticfiles()**, que permite definir la carpeta donde se encuentran los archivos estáticos de la aplicación. Se usa la carpeta resources, de esta forma el servidor sabe dónde buscar archivos como HTML, CSS, JS o imágenes, y al acceder a una URL, devuelve directamente el contenido del archivo ubicado en esa carpeta

<p align="center">
<img width="630" height="87" alt="image" src="https://github.com/user-attachments/assets/135c6cd2-1654-4029-8c4f-2c96d231fc06" />
</p>

   - La idea es acceder de esta manera: **http://localhost:35009/prueba.html**:

<p align="center">
<img width="469" height="146" alt="image" src="https://github.com/user-attachments/assets/4f42826d-fe65-4a84-8550-4b7f3b3ffd41" />
</p>

4. En este punto se implementó un ejemplo completo de uso del mini-framework: en main se configura la carpeta de archivos estáticos con **staticfiles("resources")**, se inician los servicios GET con prefijo /App y se registran dos rutas de demostración que lee los parámetros de consulta, además, el servidor entrega directamente archivos estáticos ubicados en resources/:

<p align="center">
<img width="638" height="492" alt="image" src="https://github.com/user-attachments/assets/406bbc70-3e3f-44f1-95df-e57a6bb5843d" />
</p>

- Empezamos con: **http://localhost:8080/App/pi**

<p align="center">
<img width="426" height="139" alt="image" src="https://github.com/user-attachments/assets/fad864e9-85af-4d96-8c21-5e1ab3025247" />
</p>

- **http://localhost:8080/App/hello?name=sara**

<p align="center">
<img width="411" height="117" alt="image" src="https://github.com/user-attachments/assets/709c2a87-9649-43b1-92f4-dd3111990a57" />
</p>

- **http://localhost:8080/App/hello?name=sara&name=castillo**

<p align="center">
<img width="558" height="110" alt="image" src="https://github.com/user-attachments/assets/6128b33b-f472-4ced-90ec-cc1bdf32c8ab" />
</p>

Archivos estáticos desde /resources:

- **http://localhost:8080/prueba.html**:

<p align="center">
<img width="397" height="137" alt="image" src="https://github.com/user-attachments/assets/068bc6cd-dbc9-4d66-b624-386c0ae3a77a" />
</p>

- **http://localhost:8080/index.html**

<p align="center">
<img width="1345" height="602" alt="image" src="https://github.com/user-attachments/assets/5d93ae5a-721c-4b62-8374-d9e858b0da35" />
</p>
  