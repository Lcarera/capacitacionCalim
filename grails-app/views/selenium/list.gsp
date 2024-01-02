<html>
<head>
    <title>Obtener Información de YouTube</title>
</head>
<body>
    <h1>Obtener Información de YouTube</h1>
    
    <form action="${createLink(controller: 'selenium', action: 'obtenerInformacion')}" method="post">
        <label for="url">URL del Video:</label>
        <input type="text" name="url" id="url" required>
        
        <label for="opcion">Opción:</label>
        <select name="opcion" id="opcion">
            <option value="likes">Likes</option>
            <option value="descripcion">Descripción</option>
            <option value="vistas">Vistas</option>
        </select>
    
        <input type="submit" value="Obtener Información">
    </form>
</body>
</html>