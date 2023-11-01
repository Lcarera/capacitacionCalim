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
            <input type="text" class="form-control" name="nombre" id="nombre" value="${personaje?.nombre}">
            <br>
            <label for="direccion">Puntos de salud</label>
            <input type="number" class="form-control" name="puntosSalud" id="puntosSalud" value="${personaje?.puntosSalud}">
            <br>
            <label for="anoCreacion">Puntos de fuerza</label>
            <input type="number" class="form-control" name="puntosFuerza" id="puntosFuerza" value="${personaje?.puntosFuerza}">
            <br>
            <label for="anoCreacion">Fecha de creacion</label>
            <input type="date" class="form-control" name="fechaCreacion" id="fechaCreacion" value="${personaje?.fechaCreacion}">
            <br>
            <label for="anoCreacion">Grito de guerra</label>
            <input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personaje?.gritoGuerra}">
            <br>
            
        </div>
    </div>
</body>
</html>