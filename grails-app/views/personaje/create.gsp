<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="layout" content="main">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <g:form controller="personaje" action="save">
            <g:render template="form"/>
            <div class="container-box">
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" type="submit" id="btnGuardar">Guardar</button>
            </div>
        </g:form>
    </body>
</html>