<html>

<head>
    <meta name="layout" content="main"> 
</head>

<body>
    <g:form controller="personaje" action="update">
        <g:hiddenField name="id" value="${personajeCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box">
                <a onclick="borrarSwal(${personajeCommand.id})" href="javascript:;" class="btn btn-danger">Borrar</a>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" type="submit">Guardar</button>
            </div>
        </div>
    </g:form>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function borrarSwal(id) {
        Swal.fire({
            title: 'Borrar personaje?',
            showDenyButton: true,
            confirmButtonText: 'Borrar',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = "${createLink(action: 'delete')}/" + id;
                window.location.href = url;
            }
        })
    }

    </script>
</body>

</html>