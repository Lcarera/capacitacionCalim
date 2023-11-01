<label for="nombre">Nombre:</label>
<input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}">
<br>
<label for="puntosFuerza">Puntos de Fuerza:</label>
<input type="number" class="form-control" name="puntosFuerza" id="puntosFuerza" value="${personajeCommand?.puntosFuerza}">
<br>
<label for="puntosSalud">Ano:</label>
<input type="number" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}">
<br>
<label for="puntosSalud">Ano:</label>
<input type="number" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}">
<br>
<label for="gritoGuerra">Grito de Guerra:</label>
<input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personajeCommand?.gritoGuerra}">
<br>
<label for="arma">Arma Elegida:</label>
<select id="cbArma" class="form-control" name="armaId"></select>
<br>

<script>
    $(document).ready(function () {


        $("#cbArma").select2({
            placeholder: 'Seleccione el Arma',
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
            ajaxLink : "${createLink(controller: 'editorial', action: 'ajaxGetEditoriales')}",
            idDefault : '${personajeCommand?.armaId}',
            atributo: "nombre"
        });
    });
</script>
