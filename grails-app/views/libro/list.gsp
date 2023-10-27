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
                margin-bottom: 1em;
            }
        </style>
    </head>
    <body>
        <div class="container col-8">
            <div class="container col-12 xd">
                <h1 class="librotitulo">Libros</h1>
            </div>
            <div class="dt-responsive table-responsive">
                <g:link controller="libro" action="create" class="btn btn-primary" style="float: right; margin-left: 10px">Agregar Libro</g:link>
                <table id="listLibro" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                    <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Autor</th>
                            <th>Año</th>
                            <th>Editorial</th>
                            

                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    <script>
    var tabla;
    jQuery(document).ready(function() {
        tabla = $('#listLibro').DataTable({
            //bAutoWidth: false,
            //bSortCellsTop: true,
            //BProcessing: true,
            "ordering": true,
            "searching": true,
            oLanguage: {
                sProcessing: "Buscando...",
                sSearch: "",
                sLengthMenu: "_MENU_",
                sZeroRecords: "Buscando libros",
                sInfo: "_START_ - _END_ de _TOTAL_",
                sInfoFiltered: "${message(code: 'default.datatable.infoFiltered', default: '(filtrado de MAX registros en total)')}",
                sInfoPostFix: "",
                sUrl: "",
                sInfoEmpty: "${message(code: 'default.datatable.infoEmpty', default: '0 de 0')}",
                oPaginate: {
                    "sFirst": "${message(code: 'default.datatable.paginate.first', default: 'Primero')}",
                    "sPrevious":"<",
                    "sNext": ">",
                    "sLast": "${message(code: 'default.datatable.paginate.last', default: '&Uacute;ltimo')}"
                }
            },
            //iDisplayLength: 100,
            //scrollX: true,
            aaSorting: [
                [0, 'desc']
            ],
            aoColumnDefs: [{
                    "aTargets": [0],
                    "mData": "titulo"
                },{
                    "aTargets": [1],
                    "mData": "nombreAutor",
                    'sClass': 'bold'
                },{
                    "aTargets": [2],
                    "mData": "ano"
                },{
                    "aTargets": [3],
                    "mData": "editorial.nombre"
                }],
                buttons: [],
            sPaginationType: 'simple',
            sDom: '<"row"<"col-4"l><"col-8"Bf>>t<"row"<"col-6"i><"col-6"p>>',
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                 $(nRow).off('click').on('click', function() {
                    console.log(aData);
                   window.location.href = ('${createLink(controller:"libro", action:"edit")}') + '/' + aData['id'];
                });
            }
        });

        llenarDatoslistLibro();
    });
   
    function llenarDatoslistLibro(){
        tabla.clear().draw();
        $.ajax("${createLink(controller:'libro', action:'ajaxGetLibros')}", {
            dataType: "json",
            data: { 
                
        }
        }).done(function(data) {
            console.log(data);
            tabla.rows.add(data)
            tabla.draw();
        });
    }
    </script> 
    </body>
</html>