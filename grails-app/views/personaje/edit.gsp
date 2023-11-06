<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <div class="container text-center border border-success rounded"
        style="margin-top: 20px;padding: 20px; color: rgb(72, 66, 66);">
        <g:form controller="personaje" action="update">
            <div class="row">
                <g:hiddenField name="id" value="${personajeCommand.id}" />
                <g:render template="form" />
            </div>

            <div class="row d-flex justify-content-center">
                <div style="margin-top: 15px;">
                    <button class="btn btn-danger" onclick="borrarSwal()" type="button">Borrar</button>
                    <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn" style="background-color: #39813c; color: white;" type="submit">Guardar</button>
                </div>
            </div>
        </g:form>
    </div>

    <script>
        function borrarSwal() {
            Swal.fire({
                title: 'Borrar personaje?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Borrar',
                cancelButtonText: 'Cancelar',
                denyButtonText: `No Borrar`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Borrado!', '', 'success').then((result) => {
                        window.location.href = "${createLink(action: 'delete', id: personajeCommand.id)}";
                    });
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info');
                }
            });
        }
        
    </script>

</body>

</html>