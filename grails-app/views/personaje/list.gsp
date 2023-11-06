<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <style>
        h1,
        h2 {
            display: inline-block;
            margin: 0.5em;
        }

        table {
            border-collapse: collapse;

            width: 100%;
        }

        th,
        td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
            text-align: center;

        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        th {
            background-color: #FFFAFA;
            color: #1c1c1c;

        }

        h1.personaje {
            text-align: center;
            color: white;
        }

        div.xd {
            background-color: #2D6730;
            border-radius: 5px;
            margin-bottom: 5px;
        }

        div.lol {
            background-color: #e7ebda;
            padding-top: 10px;
            padding-left: 10px;
            padding-right: 10px;
            padding-bottom: 10px;
            border-radius: 5px;
        }
    </style>
</head>

<body>
    <div class="container col-8">
        <div class="container col-12 xd">
            <h1 class="personaje">Personajes</h1>
        </div>
        <div class="dt-responsive table-responsive lol">
            <g:link controller="personaje" action="create" class="btn btn-outline-success" style="float: right; margin-left: 10px">
                Agregar personaje</g:link>
            <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Puntos de salud</th>
                        <th>Puntos de fuerza</th>
                        <th>Grito de guerra</th>
                        <th>Fecha de creacion</th>
                        <th>Arma</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <button onclick="personajeMasFuerte()" class="btn btn-primary btn-lg btn-block">Personaje Mas Fuerte</button>
        </div>

        <script>
            var tabla;
            jQuery(document).ready(function () {
                tabla = $('#listPersonaje').DataTable({
                    //bAutoWidth: false,
                    //bSortCellsTop: true,
                    //BProcessing: true,
                    "ordering": true,
                    "searching": true,
                    oLanguage: {
                        sProcessing: "Buscando...",
                        sSearch: "",
                        sLengthMenu: "_MENU_",
                        sZeroRecords: "Buscando personaje",
                        sInfo: "_START_ - _END_ de _TOTAL_",
                        sInfoFiltered: "${message(code: 'default.datatable.infoFiltered', default: '(filtrado de MAX registros en total)')}",
                        sInfoPostFix: "",
                        sUrl: "",
                        sInfoEmpty: "${message(code: 'default.datatable.infoEmpty', default: '0 de 0')}",
                        oPaginate: {
                            "sFirst": "${message(code: 'default.datatable.paginate.first', default: 'Primero')}",
                            "sPrevious": "<",
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
                        "mData": "nombre"
                    }, {
                        "aTargets": [1],
                        "mData": "puntosSalud",
                    }, {
                        "aTargets": [2],
                        "mData": "puntosFuerza"
                    }, {
                        "aTargets": [3],
                        "mData": "gritoGuerra"
                    }, {
                        "aTargets": [4],
                        "mData": "fechaCreacion"
                    }, {
                        "aTargets": [5],
                        "mData": "arma"
                    }],
                    buttons: [],
                    sPaginationType: 'simple',
                    sDom: '<"row"<"col-4"l><"col-8"Bf>>t<"row"<"col-6"i><"col-6"p>>',
                    fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                        // Row click
                        $(nRow).off('click').on('click', function () {
                             window.location.href = ('${createLink(controller:"personaje", action:"edit")}') + '/' + aData['id'];
                        });
                    }
                });

                llenarDatoslistPersonaje();
            });

            function llenarDatoslistPersonaje() {
                tabla.clear().draw();
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
                    dataType: "json",
                    data: {

                    }
                }).done(function (data) {
                    tabla.rows.add(data)
                    tabla.draw();
                });
            }

        </script>
        <script>
               function personajeMasFuerte() {
                    $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajeMasFuerte')}", {
                        dataType: "json",
                        data: {}
                    }).done(function (data) {
                        if (data) {
                            Swal.fire({
                                title: '<span style="text-transform: uppercase"><b>EL MÁS FUERTE</b></span>',
                                html: data.nombre + '<br>' +
                                    'Puntos de Fuerza: ' + data.puntosFuerza + '<br>'
                                    ,
                                confirmButtonText: 'OK'
                            });
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'No se pudo obtener el personaje más fuerte.'
                            });
                        }
                    });
                }

        </script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </div>
</body>

</html>