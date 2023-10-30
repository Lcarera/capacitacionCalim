<label for="titulo">Titulo:</label>
<input type="text" class="form-control" name="titulo" id="titulo" value="${libroCommand?.titulo}">
<br>
<label for="autor">Autor:</label>
<input type="text" class="form-control" name="autor" id="autor" value="${libroCommand?.autor}">
<br>
<label for="ano">Ano:</label>
<input type="number" class="form-control" name="ano" id="ano" value="${libroCommand?.ano}">
<br>
<label for="editorial">Editorial:</label>
<select id="cbEditorial" class="form-control" name="editorialId"></select>
<br>

<script>
    $(document).ready(function () {


        $("#cbEditorial").select2({
            placeholder: 'Seleccione la editorial',
            formatNoMatches: function() {
                return '<g:message code="default.no.elements" default="No hay elementos"/>';
            },
            formatSearching: function() {
                return 'editorial<g:message code="default.searching" default="Buscando..."/>';
            },
            minimumResultsForSearch: 1,
            formatSelection: function(item) {
                return item.text;
            }
        });

        llenarCombo({
            comboId : "cbEditorial",
            ajaxLink : "${createLink(controller: 'editorial', action: 'ajaxGetEditoriales')}",
            idDefault : '${libroCommand?.editorialId}',
            atributo: "nombre"
        });
    });
</script>
