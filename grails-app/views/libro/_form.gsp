<label for="titulo">Titulo:</label>
<input type="text" class="form-control" name="titulo" id="titulo" value="${libroCommand?.titulo}">
<br>
<label for="autor">Autor:</label>
<input type="text" class="form-control" name="autor" id="autor" value="${libroCommand?.autor}">
<br>
<label for="ano">Ano:</label>
<<<<<<< HEAD
<input type="text" class="form-control" name="ano" id="ano" value="${libroCommand?.ano}">
=======
<input type="number" class="form-control" name="ano" id="ano" value="${libroCommand?.ano}">
>>>>>>> main
<br>
<label for="editorial">Editorial:</label>
<select id="cbEditorial" class="form-control" name="editorialId"></select>
<br>

<<<<<<< HEAD
<button class="btn btn-success" type="submit">Guardar</button>
<g:link class="btn btn-secondary" controller="libro" action="list">Volver</g:link>
=======
>>>>>>> main
<script>

    $(document).ready(function () {

        swal("bienvenido a libros");
        $("#cbEditorial").select2({
            placeholder: 'Seleccione la editorial',
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
            idDefault : '${libroCommand?.editorialId}',
            atributo: "nombre"
        });
       
    });

</script>
