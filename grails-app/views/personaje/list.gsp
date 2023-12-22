<!DOCTYPE html>
<html lang="en">
<head>
    <title>Selenium Integration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function buscarVideoYoutube() {
            var link = document.getElementById('videoLink').value;
            var elemento = document.getElementById('infoElemento').value;
            console.log(link + "    " + elemento)
            $.ajax({
                url: "${createLink(controller:'selenium', action:'videoyoutube')}",
                method: "POST",
                data: { link: link, elemento: elemento }
            });
        }
    </script>
</head>
<body>
    <input type="text" id="videoLink" placeholder="Enlace del video de YouTube">
    <select id="infoElemento">
        <option value="descripcion">Descripción</option>
        <option value="vistas">Vistas</option>
        <option value="likes">Likes</option>
    </select>
    <button onclick="buscarVideoYoutube()">Buscar</button>
    
    <!-- Mostrar el resultado aquí -->
    <div id="resultado"></div>

</body>
</html>
