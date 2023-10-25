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
            h1.editorialtitulo {
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
                <h1 class="editorialtitulo">EDITORIALES</h1>
            </div>
            <table>
                <thead>
                    
                    <tr>
                        <th>Nombre</th>
                        <th>Direccion</th>
                        <th>Año Creación</th>
                        <th>Editar</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each var="editorial" in="${editoriales}">
                        <tr>
                            <td>${editorial.nombre}</td>
                            <td>${editorial.direccion}</td>
                            <td>${editorial.anoCreacion}</td>
                            <td>
                                <g:link action="edit" class="btn btn-info" params='[id:"${editorial.id}"]'>Editar</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>                
            </table>
            <g:link controller="editorial" action="create" class="btn btn-primary">Agregar Editorial</g:link>
            <g:link controller="libro" action="create" class="btn btn-primary">Agregar Libro</g:link>

        </div>
    </body>
</html>
