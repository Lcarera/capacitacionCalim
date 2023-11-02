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
    <div class="container col-12">
        <div class="container col-12 xd">
            <h1 class="personajes-titulo">Personajes</h1>
        </div>
        <div class="dt-responsive table-responsive">
            <g:link controller="personaje" action="create" class="btn btn-primary" style="float: right; margin-left: 10px">
                Agregar Personaje</g:link>
            <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                <thead>
                    <tr>
                        <th>nombre</th>
                        <th>Puntos de Fuerza</th>
                        <th>Puntos de Salud</th>
                        <th>Fecha de Creacion</th>
                        <th>grito de Guerra</th>
                        <th>Arma</th>
                        <th>Poder Total</th>

                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
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
                        sZeroRecords: "Buscando Personaje",
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
                        "mData": "puntosFuerza",
                    }, {
                        "aTargets": [2],
                        "mData": "puntosSalud"
                    }, {
                        "aTargets": [3],
                        "mData": "fecha"
                    },{
                        "aTargets": [4],
                        "mData": "gritoGuerra"
                    },{
                        "aTargets": [5],
                        "mData": "arma"
                    },{
                        "aTargets": [6],
                        "mData": "poderTotal"
                    }],
                    buttons: [],
                    sPaginationType: 'simple',
                    sDom: '<"row"<"col-4"l><"col-8"Bf>>t<"row"<"col-6"i><"col-6"p>>',
                    fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                        // Row click
                        $(nRow).off('click').on('click', function () {
                            console.log(aData);
                             window.location.href = ('${createLink(controller:"personaje", action:"edit")}') + '/' + aData['id'];
                        });
                    }
                });

                llenarDatoslistLibro();
            });

            function llenarDatoslistLibro() {
                tabla.clear().draw();
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
                    dataType: "json",
                    data: {

                    }
                }).done(function (data) {
                    console.log(data);
                    tabla.rows.add(data)
                    tabla.draw();
                });
            }

        </script>
    </div>
</body>

</html>