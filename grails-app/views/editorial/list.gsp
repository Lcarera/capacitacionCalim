<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <style>
            h1, h2 {
                display: inline-block;
                margin: 0.5em;
            }
        </style>
    </head>
    <body>
    <g:link controller="editorial" action="create">Agregar Editorial</g:link>
        <g:each var="editorial" in="${editoriales}">
            <div>
                <g:link action="edit" params='[id:"${editorial.id}"]'>
                    <h1>${editorial.nombre}</h1><h2>(${editorial.direccion})</h2><h2>(${editorial.anocreacion})</h2>
                </g:link>
            </div>
        </g:each>
    </body> 
</html>
