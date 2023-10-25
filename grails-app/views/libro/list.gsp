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
        <script src="sweetalert2.all.min.js"></script>
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
                                <g:link action="edit" class="btn btn-info" params='[id:"${libro.id}"]'>Editar</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>                
            </table>
            <br>
            <g:link controller="libro" action="create" class="btn btn-primary">Agregar Libro</g:link>
            <a class="btn btn-primary" href='javascript:;' onclick="llamarswal()">Crear Libro</a>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
            console.log("${createLink(action:'create')}")
            function llamarswal() {
            Swal.fire({
              title: 'Estas seguro?',
              text: "Vas a crear un libro",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Si, vamos!'
            }).then((result) => {
              if (result.isConfirmed) {
                  window.location.href="${createLink(action:'create')}"
              }
            })
            }
            </script>
        </div>
    </body>
</html>