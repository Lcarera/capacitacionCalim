<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container text-center border border-success rounded"
        style="margin-top: 20px;padding: 20px; color: rgb(72, 66, 66);">
            <g:form controller="personaje" action="save">
                <div class="row">
                    <g:render template="form"/>
                </div>
    
                
                <div class="row d-flex justify-content-center">
                    <div style="margin-top: 15px;">    
                        <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                        <button class="btn" style="background-color: #39813c; color: white;" type="submit">Guardar</button>              
                    </div>
                </div>
            </g:form>
        </div>
        
    </body> 
</html>
