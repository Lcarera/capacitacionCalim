<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
</head>

<body>

    <g:form controller="personaje" action="update">
        <g:hiddenField name="id" value="${personajeCommand?.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box">
                <br>
                <!-- <button class="btn btn-success" type="submit">Guardar</button> -->
                <!-- <a onclick="borrarSwal()" id="${personajeCommand?.id}" href="javascript:;" class="btn btn-danger ">Borrar</a> -->
                <button class="btn btn-success" type="button" onclick="GuardarSwal()" id="${personajeCommand?.id}">Guardar</button>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-danger" type="button" onclick="borrarSwal()" id="${personajeCommand?.id}">Borrar</button>
            </div>
        </div>
    </g:form>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function borrarSwal() {
            Swal.fire({
                title: 'Borrar personaje?',
                showDenyButton: true,
                confirmButtonText: 'Borrar',
                denyButtonText: `Cancelar`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Borrado!', '', 'success')
                    window.location.href = "${createLink(action:'delete',)}/" + '${personajeCommand?.id}'
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info')
                }
            })
        }
        function GuardarSwal() {
            Swal.fire({
                title: 'Confirmar cambios?',
                showDenyButton: true,
                confirmButtonText: 'Guardar',
                denyButtonText: `Cancelar`,
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Guardado!', '', 'success')
                    document.querySelector("form").submit()
                    // window.location.href = "${createLink(action:'update',)}/" + '${personajeCommand?.id}'
                } else if (result.isDenied) {
                    Swal.fire('Guardado Cancelado', '', 'info')
                }
            })
        } 

    </script>
</body>

</html>