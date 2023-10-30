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

                <label for="titulo">Titulo:</label>
                <input type="text" class="form-control" name="titulo" id="titulo" value="${libroCommand?.titulo}">
                <br>
                <label for="autor">Autor:</label>
                <input type="text" class="form-control" name="autor" id="autor" value="${libroCommand?.autor}">
                <br>
                <label for="ano">Ano:</label>
                <input type="text" class="form-control" name="ano" id="ano" value="${libroCommand?.ano}">
                <br>
                <label for="editorial">Editorial:</label>
                <select id="cbEditorial" class="form-control" name="editorialId"></select>
                <br>
                <br>
                <button class="btn btn-success" type="submit">Guardar</button>
                <g:link class="btn btn-secondary" controller="libro" action="list">Volver</g:link>

            </div>
        </div>
    </body>
</html>

<script>
    $(document).ready(function () {
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
