<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Videos</title>
    </head>
    <body>
        <g:form controller="selenium" action="buscarVideo">
            <label for="titulo">Video Title:</label>
            <input type="text" id="titulo" name="titulo" required>
            <button type="submit">Search</button>
        </g:form>
        <div id="videoInfo">
            <h2>Video Information</h2>
            <p id="likes">Likes: <span></span></p>
            <p id="description">Description: <span></span></p>
            <p id="comments">Comments: <span></span></p>
        </div>
    </body>
</html>
