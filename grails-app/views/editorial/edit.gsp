<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <g:form controller="editorial" action="update">
            <g:hiddenField name="id" value="${editorialCommand.id}"/>
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">
                    <g:link controller="editorial" action="delete" id="${editorialCommand.id}" class="btn btn-danger ">Borrar</g:link>
                    
                    <g:link controller="editorial" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn btn-success" type="submit">Guardar</button>
                </div>
            </div>
        </g:form>
    </body> 
</html>
