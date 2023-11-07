<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
         <style>
        .container-right {
        text-align: right;
        }
    </style>
    </head>
    <body>
        <g:form controller="personaje" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box container-right">    
                    <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn btn-success" type="submit" id="submitButton" style="background-color: #16b179;">Guardar</button>                
                </div>
            </div>
        </g:form>
    </body> 
</html>
