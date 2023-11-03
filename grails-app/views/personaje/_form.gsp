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
                <input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}">
                <br>
                <label for="puntosSalud">Puntos de Salud</label>
                <input type="number" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}">
                <br>
                <label for="puntosFuerza">Puntos de Fuerza</label>
                <input type="number" class="form-control" name="puntosFuerza" id="puntosFuerza" value="${personajeCommand?.puntosFuerza}">
                <br>
                <label for="gritoGuerra">Grito de Guerra</label>
                <input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personajeCommand?.gritoGuerra}">
                <label for="arma">Arma:</label>
                <select id="cbArma" class="form-control" name="armaId"></select>
                <br>
            </div>
        </div>
        <br>
    </body>
</html>

<script>
    $(document).ready(function () {

        $("#cbArma").select2({
            placeholder: 'Seleccione el arma',
            formatNoMatches: function() {
                return '<g:message code="default.no.elements" default="No hay elementos"/>';
            },
            formatSearching: function() {
                return '<g:message code="default.searching" default="Buscando..."/>';
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
