<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="editorial" action="update">
            <g:hiddenField name="id" value="${editorial.id}"/>
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">
                    <button type="button" onclick=borrar() id="${editorial.id}" class="btn btn-danger deleteButton">Borrar</button>         
                    <g:link controller="editorial" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn btn-success" onclick=guardar() type="submit">Guardar</button>
                </div>
            </div>
        </g:form>
        <script>
function guardar(){
    swal({
        title: "Estas seguro?",
        text: "Estas seguro que queres editar una editorial?",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "confirmar",
        cancelButtonText: "cancelar",
        closeOnConfirm: true,
        closeOnCancel: true
    },
        function(isConfirm) {
        if (isConfirm) {
            window.location.href = "${createLink(action:'create')}";
        }
    });
}
function borrar(){
    swal({
        title: "Estas seguro?",
        text: "Estas seguro que deseas eliminar la editorial?",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "confirmar",
        cancelButtonText: "cancelar",
        closeOnConfirm: true,
        closeOnCancel: true
    },
        function(isConfirm) {
        if (isConfirm) {
            window.location.href = "${createLink(action:'delete') + '/' + editorial.id}";
        }
    });
}
        </script>
    </body> 
</html>
