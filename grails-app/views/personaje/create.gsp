<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="personaje" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">    
                    <button class="btn btn-success" type="submit">Guardar</button>
                    <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>                
                </div>
            </div>
        </g:form>
    </body> 
</html>
