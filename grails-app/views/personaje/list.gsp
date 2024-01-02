<!DOCTYPE html>
<html lang="en">
<head>
    <title>Selenium Integration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    function buscarVideoYoutube() {
        var link = document.getElementById('videoLink').value;
        var elemento = document.getElementById('infoElemento').value;
        $.ajax({
            url: "${createLink(controller:'selenium', action:'videoyoutube')}",
            method: "POST",
            data: { link: link, elemento: elemento },
            success: function(data) {

                document.getElementById('resultado').innerText = data;
            }
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

    <div id="resultado"></div>

</body>
</html>
