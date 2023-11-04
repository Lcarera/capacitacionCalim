<label for="nombre">Nombre:</label>
<input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}">
<br>
<label for="puntosSalud">Puntos de Salud:</label>
<input type="text" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}">
<br>
<label for="puntosFuerza">Puntos de Fuerza:</label>
<input type="number" class="form-control" name="ano" id="ano" value="${personajeCommand?.puntosFuerza}">
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
