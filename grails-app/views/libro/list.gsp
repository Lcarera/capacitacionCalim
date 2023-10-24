<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <style>
            h1, h2 {
                display: inline-block;
                margin: 0.5em;
            }
            table {
                border-collapse: collapse;
                
                width: 100%;
            }

            th, td {
                border-bottom: 1px solid #ddd;
                padding: 8px;
                text-align: center;
                
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            th {
                background-color: #4CAF50;
                color: white;
                
            }
            h1.librotitulo {
                text-align: center;
                color: white;
            }
            div.xd {
                background-color: #39813C;
            }
        </style>
    </head>
    <body>
        <div class="container col-8">
            <div class="container col-12 xd">
                <h1 class="librotitulo">LIBROS</h1>
            </div>
            <table>
                <thead>
                    
                    <tr>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Año</th>
                        <th>Editorial</th>
                        <th>Editar</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each var="libro" in="${libros}">
                        <tr>
                            <td>${libro.titulo}</td>
                            <td>${libro.autor}</td>
                            <td>${libro.ano}</td>
                            <td>${libro.editorial.nombre}</td>
                            <td>
                                <g:link action="edit" class="btn btn-info" params='[id:"${libro.id}", ]'>Editar</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>                
            </table>
            <g:link controller="libro" action="create" class="btn btn-primary">Agregar Libro</g:link>
        </div>
    </body>
</html>
