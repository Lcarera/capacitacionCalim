<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Videos</title>
        <style>
            .error {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <g:form controller="selenium" action="buscarVideo">
            <label for="titulo">Video Title:</label>
            <input type="text" id="titulo" name="titulo" value="${titulo}" required>
            <button type="submit">Search</button>
        </g:form>

        <g:if test="${flash.message}">
            <div class="error">${flash.message}</div>
        </g:if>

        <label for="infoSelector">Select Information:</label>
        <select id="infoSelector" onchange="showSelectedInfo()">
            <option value="select">Select Info</option>
            <option value="likes">Likes</option>
            <option value="description">Description</option>
            <option value="comments">Comments</option>
        </select>

        <div id="videoInfo">
            <h2>Video Information</h2>
            <div id="likesInfo" style="display: none;">
                <p id="likes">Likes: <span>${videoInfo?.likes}</span></p>
            </div>
            <div id="descriptionInfo" style="display: none;">
                <p id="description">Description: <span>${videoInfo?.description}</span></p>
            </div>
            <div id="commentsInfo" style="display: none;">
                <p id="comments">Comments: <span>${videoInfo?.commentsCount}</span></p>
            </div>
        </div>

        <script>
            function showSelectedInfo() {
                var selectedOption = document.getElementById("infoSelector").value;

                // Ocultar todas las secciones de información
                document.getElementById("likesInfo").style.display = "none";
                document.getElementById("descriptionInfo").style.display = "none";
                document.getElementById("commentsInfo").style.display = "none";

                // Mostrar la sección de información correspondiente a la opción seleccionada
                if (selectedOption !== "select") {
                    document.getElementById(selectedOption + "Info").style.display = "block";
                }
            }
        </script>
    </body>
</html>