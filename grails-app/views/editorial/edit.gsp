<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="editorial" action="update">
            <g:hiddenField name="id" value="${editorial.id}"/>
            <g:render template="form"/>
            <g:link controller="editorial" action="delete" id="${editorial.id}" class="btn btn-danger">Borrar</g:link>
        </g:form>
    </body> 
</html>
