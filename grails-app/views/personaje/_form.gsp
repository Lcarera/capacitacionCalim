<label for="nombre">Nombre:</label>
<input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}" placeholder="Nombre">
<br>
<label for="puntosSalud">Puntos de Salud:</label>
<input type="text" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}" placeholder="Puntos de Salud">
<br>
<label for="puntosFuerza">Puntos de Fuerza:</label>
<input type="number" class="form-control" name="puntosFuerza" id="puntosFuerza" value="${personajeCommand?.puntosFuerza}" placeholder="Puntos de Fuerza">
<br>
<label for="puntosFuerza">Grito de Guerra:</label>
<input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personajeCommand?.gritoGuerra}" placeholder="Grito de Guerra">
<br>
<label for="arma">Arma:</label>
<select id="cbArma" class="form-control" name="armaId" ></select>
<br>
<br>
<br>

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
