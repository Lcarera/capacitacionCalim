<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="libro" action="update">
            <g:hiddenField name="id" value="${libroCommand.id}"/>
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">
                    <button type="button" onclick="confirmarBorrar()" id="${libroCommand.id}" class="btn btn-danger ">Borrar</button>
                    <%-- <g:link controller="libro" action="delete" id="${libroCommand.id}" class="btn btn-danger ">Borrar</g:link>
                    <g:link controller="libro" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn btn-success" type="submit">Guardar</button> --%>
                </div>
            </div>
        </g:form>

        <script>
            function confirmarBorrar(){
                swal({
                        title: "¿Estás seguro?",
                        text: "La condición se eliminará",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, eliminar",
                        cancelButtonText: "No, cancelar",
                        closeOnConfirm: true,
                        closeOnCancel: true
                    },
                    function(isConfirm) {
                        if (isConfirm) {
                            window.location.href = "${createLink(action:'delete')}/${libroCommand.id}";
                        }
                    }
                );      
            };
        </script>
    </body> 
</html>