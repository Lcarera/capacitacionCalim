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
                <button class="btn btn-success" id="guardarButton" type="button">Guardar</button>             
            </div>
        </div>
    </g:form>

    <script>
        function borrarSwal() {
            
            Swal.fire({
                title: 'Borrar Personaje?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Borrar',
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
            /*
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
                $('#personajeForm').submit();
            } else if (result.isDenied) {
                Swal.fire('Guardado Cancelado', '', 'info');
                
                // Handle the case when the user denies the action.
            }
            jQuery(document).ready(function () {
            event.preventDefault()
            });
        }
        */
        $(document).ready(function() {
            $("#guardarButton").click(function() {
                Swal.fire({
                    title: 'Confirmar cambios?',
                    showCancelButton: true,
                    confirmButtonText: 'Guardar',
                    cancelButtonText: 'Cancelar',
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire('Guardado!', '', 'success');
                        // Proceed with the form submission.
                        document.querySelector("form").submit();                    
                    }
                });
            });
        });

    </script>
</body>

</html>