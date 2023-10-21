<label for="titulo">Titulo:</label>
<input type="text" name="titulo" id="titulo" value="${libro?.titulo}">
<br>
<label for="autor">Autor:</label>
<input type="text" name="autor" id="autor" value="${libro?.autor}">
<br>
<label for="editorial">Editorial:</label>
<select id="cbEditorial" name="editorialId"></select>
<br>
<button type="submit">Guardar</button>
<g:link controller="libro" action="list">Volver</g:link>

<script>

    $(document).ready(function () {
        $("#cbEditorial").select2({
            placeholder: 'Seleccione una Editorial',
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
            comboId : "cbEditorial",
            ajaxLink : "${createLink(controller: 'editorial', action: 'ajaxGetEditoriales')}",
            idDefault : '${libro?.editorialId}',
        });
    });
</script>