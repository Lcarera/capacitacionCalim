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
            <label for="nombre">Titulo</label>
            <input type="text" class="form-control" name="nombre" id="nombre" value="${libro?.titulo}">
            <br>
            <label for="direccion">Autor</label>
            <input type="text" class="form-control" name="direccion" id="direccion" value="${libro?.autor}">
            <br>
            <label for="anoCreacion">Año de Lanzamiento</label>
            <input type="text" class="form-control" name="anoCreacion" id="anoCreacion" value="${libro?.ano}">
            <br>
            
        </div>
    </div>
</body>
</html>
