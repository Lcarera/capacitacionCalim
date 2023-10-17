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
    <g:link controller="libro" action="create">Agregar Libro</g:link>
        <g:each var="libro" in="${libros}">
            <div>
                <g:link action="edit" params='[id:"${libro.id}"]'>
                    <h1>${libro.titulo}</h1><h2>(${libro.autor})</h2>
                </g:link>
            </div>
        </g:each>
    </body> 
</html>
