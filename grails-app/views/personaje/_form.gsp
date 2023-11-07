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
<input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personajeCommand?.gritoGuerra == '-' ? '' : personajeCommand?.gritoGuerra}" placeholder="Grito de Guerra">
<br>
<label for="arma">Arma:</label>
<select id="cbArma" class="form-control" name="armaId"></select>
<br>
<br>
<div id="error-message" style="color: red;"></div>
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
        }});

    llenarCombo({
        comboId : "cbArma",
        ajaxLink : "${createLink(controller: 'personaje', action: 'ajaxGetArmas')}",
        idDefault : '${personajeCommand?.armaId}',
        atributo: "nombre"
    });

    $("#submitButton").click(function() {
        var nombreValue = $("#nombre").val();
        var puntosFuerzaValue = $("#puntosFuerza").val();
        var puntosSaludValue = $("#puntosSalud").val();

        if (nombreValue.trim() === "") {
            $("#error-message").text("El campo 'Nombre' no puede estar vacío.");
            return false;
        }

        if (puntosSaludValue.trim() === "") {
            $("#error-message").text("El campo 'Puntos de Salud' no puede estar vacío.");
            return false;
        }

        if (puntosFuerzaValue.trim() === "") {
            $("#error-message").text("El campo 'Puntos de Fuerza' no puede estar vacío.");
            return false;
        }

        if ($("#cbArma").val() === null) {
            $("#error-message").text("Debe seleccionar un arma antes de guardar el personaje.");
            return false;
        }


        $(document).ready(function() {
                Swal.fire({
                    title: 'Confirmar cambios?',
                    icon: 'question',
                    showDenyButton: true,
                    confirmButtonText: 'Guardar',
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.querySelector("form").submit();                    
                    } else if (result.isDenied) {
                        Swal.fire('Guardado Cancelado', '', 'info');
                    }
                });
            ;
        });
        return true;
    });
    
});
</script>
