<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    

</head>

<body>
    <g:form controller="personaje" action="update" id="personajeForm">
        <g:hiddenField name="id" value="${personajeCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box">
                <a onclick="borrarSwal()" id="${personajeCommand.id}" href="javascript:;" class="btn btn-danger ">Borrar</a>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" onclick="guardarSwal()" type="button" style="background-color: #16b179;">Guardar</button>             </div>
        </div>
    </g:form>

    <script>
        function borrarSwal() {
            
            Swal.fire({
                title: 'Borrar Personaje?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Borrar',
                cancelButtonText: 'Cancelar',
                denyButtonText: `No Borrar`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Borrado!', '', 'success')
                    window.location.href = "${createLink(action:'delete',)}/" + '${personajeCommand.id}'
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info')
                }
            })
        }
            
        async function guardarSwal() {
            const result = await Swal.fire({
                title: 'Confirmar cambios?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Guardar',
                cancelButtonText: 'Cancelar',
            });

            if (result.isConfirmed) {
                Swal.fire('Guardado!', '', 'success');
                // Proceed with your action after user confirmation.
                document.getElementById('personajeForm').submit(); // Submit the form
            } else if (result.isDenied) {
                Swal.fire('Guardado Cancelado', '', 'info');
                // Handle the case when the user denies the action.
            }
        }
    </script>
    <script src="sweetalert2.all.min.js"></script>
</body>

</html>