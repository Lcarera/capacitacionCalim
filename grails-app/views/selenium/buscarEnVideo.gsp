<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="layout" content="main">
</head>

<body>
    <g:form controller="selenium" action="ajaxGetVideoData">
        <label for="busqueda">¿Qué deseas buscar?:</label>
        <input type="text" id="busqueda" name="busqueda" /><br /><br />

        <label for="buscarPor">Buscar Por:</label>
        <select name="buscarPor" id="buscarPor">
            <option value="descripcion">Descripción</option>
            <option value="likes">Cantidad de likes</option>
            <option value="comentarios">Cantidad de comentarios</option>
        </select><br /><br />

        <input id="botonSubmit" type="submit" value="Buscar" />
    </g:form>
    <p id="pVideoData"></p>

    <script>
        const formulario = document.getElementsByTagName("form")[0];
        const botonSubmit = document.getElementById("botonSubmit");

        botonSubmit.addEventListener("click", async (e) => {
            e.preventDefault();
            
            const respuesta = await fetch('${createLink(controller:"selenium", action:"ajaxGetVideoData")}', {
                method: 'POST',
                body: new FormData(formulario)
            })
            .then(response => {
                return response.text();
            })
            .catch(error => {
                console.log(error);
            });

            const pVideoData = document.getElementById("pVideoData");
            pVideoData.innerText = respuesta ?? "Ha ocurrido un error al conectar con el video solicitado";
        })
    </script>
</body>

</html>