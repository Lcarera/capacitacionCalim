<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="libro" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">   
                <br>
                <button class="btn btn-succes" type="submit">Guardar</button>
                <g:link class="btn btn-secondary" controller="libro" action="list">Volver</g:link>                 
                </div>
            </div>
        </g:form>
    </body> 
</html>
