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
                    <button type="button" onclick=mensaje() id="${libroCommand.id}" class="btn btn-danger deleteButton">Borrar</button>         
            </div>
        </g:form>
               <script>
function mensaje(){
    swal({
        title: "Estas seguro?",
        text: "Estas seguro que deseas eliminar el libro?",
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
            window.location.href = "${createLink(action:'delete') + '/' + libroCommand.id}";
        }
    });
}
        </script>
    </body> 
</html>
