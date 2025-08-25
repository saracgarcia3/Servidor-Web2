document.getElementById("fileForm").addEventListener("submit", async function(event) {
  event.preventDefault();

  const fileType = document.getElementById("fileType").value;

  try {
   
    const response = await fetch(`/files?type=${fileType}`);
    if (!response.ok) throw new Error("Error al obtener archivos");

    const files = await response.json();

    let resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "<h3>Archivos encontrados:</h3>";

    if (files.length === 0) {
      resultsDiv.innerHTML += "<p>No se encontraron archivos.</p>";
    } else {
      let list = "<ul>";
      files.forEach(file => {
        list += `<li><a href="${file}" target="_blank">${file}</a></li>`;
      });
      list += "</ul>";
      resultsDiv.innerHTML += list;
    }

  } catch (error) {
    document.getElementById("results").innerHTML = `<p style="color:red;">${error.message}</p>`;
  }
});
