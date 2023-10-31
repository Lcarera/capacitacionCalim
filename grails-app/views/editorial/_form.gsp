<!DOCTYPE html>
<html>
<head>
    
    <style>
        .center-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            
        }

        .container-box {
            max-width: 400px;
        }
    </style>
</head>
<body>
    <div class="center-content">
        <div class="container-box">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" name="nombre" id="nombre" value="${editorialCommand?.nombre}">
            <br>
            <label for="direccion">Dirección</label>
            <input type="text" class="form-control" name="direccion" id="direccion" value="${editorialCommand?.direccion}">
            <br>
            <label for="anoCreacion">Año de Creación</label>
            <input type="text" class="form-control" name="anoCreacion" id="anoCreacion" value="${editorialCommand?.anoCreacion}">
            <br>
            
        </div>
    </div>
</body>
</html>
