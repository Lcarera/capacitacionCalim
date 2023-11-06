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
                <button class="btn btn-success" type="submit">Guardar</button>
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
        
        function GuardarSwalAndSubmit() {
            Swal.fire({
                title: 'Confirmar cambios?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Guardado',
                cancelButtonText: 'Cancelar',
                denyButtonText: `No Guardado`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Guardado!', '', 'success');
                    // Si se confirma, envía el formulario
                    $("#personajeForm").submit();
                } else if (result.isDenied) {
                    Swal.fire('Guardado Cancelado', '', 'info');
                }
            });
        }

        $(document).ready(function() {
            // Captura el evento de clic en el botón "Guardar"
            $('.btn-success').on('click', function(event) {
                event.preventDefault(); // Evita que se envíe el formulario de inmediato
                GuardarSwalAndSubmit(); // Muestra el GuardarSwal y envía el formulario si es confirmado
            });
        });
    </script>
    <script src="sweetalert2.all.min.js"></script>
</body>

</html>