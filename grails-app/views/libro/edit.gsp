<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <g:form controller="libro" action="update">
        <g:hiddenField name="id" value="${libroCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box">
                <a onclick="borrarSwal()" id="${libroCommand.id}" href="javascript:;" class="btn btn-danger ">Borrar</a>
                <g:link controller="libro" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" type="submit">Guardar</button>
            </div>
        </div>
    </g:form>

    <script>
        function borrarSwal() {
            Swal.fire({
                title: 'Borrar libro?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Borrar',
                cancelButtonText: 'Cancelar',
                denyButtonText: `No Borrar`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Libro Borrado!', '', 'success')
                    window.location.href = "${createLink(action:'delete',)}/" + '${libroCommand.id}'
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info')
                }
            })
        }
        /* function GuardarSwal() {
            Swal.fire({
                title: 'Confirmar cambios?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Guardado',
                cancelButtonText: 'Cancelar',
                denyButtonText: `No Guardado`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Guardado!', '', 'success')
                } else if (result.isDenied) {
                    Swal.fire('Guardado Cancelado', '', 'info')
                }
            })
        } */

    </script>
</body>
</html>