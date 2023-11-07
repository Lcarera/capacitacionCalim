<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="layout" content="main">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <g:form controller="personaje" action="update">
            <g:hiddenField name="id" value="${personajeCommand.id}" />
            <g:render template="form" />
            <div class="container-box">
                <g:link controller="personaje" class="btn btn-outline-secondary" action="list">Volver</g:link>
                <button id="btnBorrar" class="btn btn-outline-danger">Borrar</button>
                <button id="btnGuardar" class="btn btn-success" type="submit">Guardar</button>
            </div>
        </g:form>
    </body>
</html>