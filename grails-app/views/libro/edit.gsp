<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="libro" action="update">
            <g:hiddenField name="id" value="${libro.id}"/>
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">
                    <g:link controller="libro" action="delete" id="${libro.id}" class="btn btn-danger ">Borrar</g:link>
                </div>
            </div>
        </g:form>
    </body> 
</html>
