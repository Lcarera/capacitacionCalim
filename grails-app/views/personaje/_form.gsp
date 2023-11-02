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
            <label for="arma">Arma:</label>
            <select id="cbArma" class="form-control" name="armaId"></select>
            <br>
            
        </div>
    </div>

    <script>
    $(document).ready(function () {


        $("#cbArma").select2({
            placeholder: 'Seleccione el arma',
            formatNoMatches: function() {
                return '<g:message code="default.no.elements" default="No hay elementos"/>';
            },
            formatSearching: function() {
                return 'arma<g:message code="default.searching" default="Buscando..."/>';
            },
            minimumResultsForSearch: 1,
            formatSelection: function(item) {
                return item.text;
            }
        });

        llenarCombo({
            comboId : "cbArma",
            ajaxLink : "${createLink(controller: 'personaje', action: 'ajaxGetArmas')}",
            idDefault : '${personajeCommand?.armaId}',
            atributo: "nombre"
        });
    });
</script>
</body>
</html>