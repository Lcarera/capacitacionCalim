<label for="nombre">Nombre:</label>
<input type="text" class="form-control" name="nombre" id="nombre" value="${personajeCommand?.nombre}">
<br>
<label for="puntosSalud">Puntos de salud:</label>
<input type="number" class="form-control" name="puntosSalud" id="puntosSalud" value="${personajeCommand?.puntosSalud}">
<br>
<label for="puntosFuerza">Puntos de fuerza:</label>
<input type="number" class="form-control" name="puntosFuerza" id="puntosFuerza" value="${personajeCommand?.puntosFuerza}">
<br>
<label for="gritoGuerra">Grito de guerra:</label>
<input type="text" class="form-control" name="gritoGuerra" id="gritoGuerra" value="${personajeCommand?.gritoGuerra == '-' ? '' : personajeCommand?.gritoGuerra}">
<br>
<label for="cbArma">Arma:</label>
<select id="cbArma" class="form-control" name="armaId"></select>

<script>
    $(document).ready(() => {
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
        
        $("#btnBorrar").on("click", event => {
            event.preventDefault();

            swal({
                title: "¿Eliminar personaje?",
                icon: "warning",
                dangerMode: true,
                buttons: ["Cancelar", "Borrar"]
            })
            .then(result => {
                if (result) {
                    window.location.href = `${createLink(controller: "personaje", action: "delete", id: personajeCommand?.id)}`;
                }
            });
        });

        $("#btnGuardar").on("click", event => {
            event.preventDefault();
            
            swal({
                title: "¿Guardar cambios?",
                icon: "info",
                buttons: ["Cancelar", "Guardar"]
            })
            .then(result => {
                if (result) {
                    document.querySelector("form").submit();
                }
            });
        });
    });
</script>