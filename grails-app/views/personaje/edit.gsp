<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
     <style>
    .container-right {
    text-align: right;
    }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <g:form controller="personaje" action="update">
        <g:hiddenField name="id" value="${personajeCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box container-right">
                <a onclick="borrarSwal()" id="${personajeCommand.id}" href="javascript:;" class="btn btn-danger ">Borrar</a>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" id="submitButton" type="button" style="background-color: #16b179;">Guardar</button> 
           </div>
        </div>
    </g:form>

    <script>
        function borrarSwal() {
            Swal.fire({
                title: 'Borrar personaje',
                text: 'Esta seguro que desea borrar el personaje?',
                showCancelButton: true,
                confirmButtonText: 'Borrar',
                icon: 'question',
                cancelButtonText: 'Cancelar',
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Borrado!', '', 'success')
                    window.location.href = "${createLink(action:'delete',)}/" + '${personajeCommand.id}'
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info')
                }
            })
        }

    
    </script>
    <script src="sweetalert2.all.min.js"></script>
</body>

</html>