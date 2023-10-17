<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="libro" action="update">
            <g:hiddenField name="id" value="${libro.id}"/>
            <g:render template="form"/>
            <g:link controller="libro" action="delete" id="${libro.id}" class="btn btn-danger">Borrar</g:link>
        </g:form>
    </body> 
</html>
