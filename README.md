<h1 align="center"> ​Servidor Web en Java💻​ </h1>
<p align="center">Proyecto que sirve archivos estáticos de tipo HTML, archivos java script, css e imagenes desde una carpeta "resources", incluye una interfaz web interactuar con archivos mediante un buscador y pruebas de servicios REST.</p>

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


  🧾​ Pruebas
- Prueba de REST (simulada): fetch en script.js hacia /rest/files?type=<tipo> y /rest/hello, se comprueba el manejo de errores si el archivo o endpoint no existe
