<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>

    <body>
    <br>
        <g:form controller="personaje" action="update">
        <g:hiddenField name="id" value="${personajeCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box">
                <a onclick="borrarSwal()" id="${personajeCommand.id}" href="javascript:;" class="btn btn-danger ">Borrar</a>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" type="submit">Guardar</button>
            </div>
        </div>
        </g:form>

        <script>
            function borrarSwal() {
            Swal.fire({
                title: 'Borrar Personaje',
                text: "No vas a poder revertir estos cambios.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: 'Cancelar', 
                confirmButtonText: 'Si, Borrar'
                }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    )
                    window.location.href = "${createLink(action:'delete',)}/" + '${personajeCommand.id}'
                }
            });
        }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>

</html>