<label for="nombre">Nombre:</label>
<input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}">
<br>
<label for="puntosDeFuerza">Puntos De Fuerza:</label>
<input type="number" class="form-control" name="puntosDeFuerza" id="puntosDeFuerza" value="${personajeCommand?.puntosDeFuerza}">
<br>
<label for="puntosDeSalud">Puntos De Salud:</label>
<input type="number" class="form-control" name="puntosDeSalud" id="puntosDeSalud" value="${personajeCommand?.puntosDeSalud}">
<br>
<label for="gritoDeGuerra">Grito De Guerra:</label>
<input type="text" class="form-control" name="gritoDeGuerra" id="gritoDeGuerra" value="${personajeCommand?.gritoDeGuerra}">
<br>
<label for="arma">Arma:</label>
<select id="cbArma" class="form-control" name="armaId"></select>
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
            ajaxLink : "${createLink(controller: 'arma', action: 'ajaxGetArmas')}",
            idDefault : '${personajeCommand?.armaId}',
            atributo: "nombre"
        });
    });
</script>
