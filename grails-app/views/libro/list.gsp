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
        <g:each var="libro" in="${libros}">
            <div>
                <h1>${libro.titulo}</h1><h2>(${libro.autor})</h2>
            </div>
        </g:each>
    </body> 
</html>
