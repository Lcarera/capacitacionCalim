<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información video Youtube</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        select, input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
        }
    </style></head>
<body>
    <label for="titulo">Titulo del video:</label>
    <input type="text" id="titulo" placeholder="Ingrese el título del video">

    <label for="tipoInformacion">Elije que quieres obtener del video:</label>
    <select id="tipoInformacion" class="js-example-basic-single">
        <option value="Likes">Likes</option>
        <option value="Comentarios">Comentarios</option>
        <option value="Descripcion">Descripcion</option>
    </select>

   <br>
   <br>
   <br>
    <button onclick="buscarVideo()">Buscar</button>

    <div id="resultContainer"></div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.js-example-basic-single').select2();
        });

        function buscarVideo() {
            var tipoInformacion = $("#tipoInformacion").val();
            var titulo = $("#titulo").val();

            $.ajax("${createLink(controller:'selenium', action:'ajaxGetInformacionVideo')}", {
                dataType: "json",
                data:{
                    tipoInformacion: tipoInformacion,
                    titulo: titulo
                }
            }).done(function (data) {
                $("#resultContainer").html(data.result);
            });
        }
    </script>

</body>
</html>
