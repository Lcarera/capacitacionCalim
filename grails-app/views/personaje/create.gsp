<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <style>
                h1.personaje {
                    display: block;
                    margin: 0 auto;
                    text-align: center;
                    padding: 10px;
                }
        </style>
    </head>
    <body>
    <div class="container col-12">
            <h1 class="personaje">Crear personaje</h1>
    </div>
        <g:form controller="personaje" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">   
                    <br> 
                    <button class="btn btn-success" type="submit">Guardar</button> 
                    <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>               
                </div>
            </div>
        </g:form>
    </body> 
</html>
