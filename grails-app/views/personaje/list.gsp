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

        h1.personajetitulo {
            text-align: center;
            color: white;
        }

        div.xd {
            background-color: #39813C;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <div class="container col-8">
        <div class="container col-12 xd">
            <h1 class="personajetitulo">PERSONAJES</h1>
        </div>
        <div class="dt-responsive table-responsive" style="margin-top: 10px;">
            <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Puntos De Fuerza</th>
                        <th>Puntos De Salud</th>
                        <th>Fecha De Creacion</th>
                        <th>Grito De Guerra</th>
                        <th>Arma</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
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
                    "mData": "puntosDeFuerza",
                }, {
                    "aTargets": [2],
                    "mData": "puntosDeSalud",
                }, {
                    "aTargets": [3],
                    "mData": "fechaCreacion"
                }, {
                    "aTargets": [4],
                    "mData": "gritoDeGuerra"
                }, {
                    "aTargets": [5],
                    "mData": "arma"
                }],
                buttons: [{
                    text: 'Agregar Personaje',
                    action: function () {
                        window.location.href = ('${createLink(controller:"personaje", action:"create")}');
                    },
                    className: 'btn btn-primary',
                },
                {
                    text: 'Personaje Más Fuerte',
                    action: function () {
                        obtenerPersonajeMasFuerte();
                    },
                    className: 'btn btn-primary',

                },],
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

            llenarDatoslistPersonaje();
        });

        function llenarDatoslistPersonaje() {
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

        function personajeMasFuerteSwal(data) {
            if (data.length > 0) {
                const personajeFuerte = data[0];
                Swal.fire({
                    title: 'El personaje más fuerte es: ' + personajeFuerte.nombre,
                    text: 'Su daño en total es: ' + personajeFuerte.daño,
                    imageUrl: 'https://i.pinimg.com/originals/c6/25/90/c62590c1756680060e7c38011cd704b5.jpg',
                    imageWidth: 400,
                    imageHeight: 400,
                    imageAlt: 'Custom image',
                });
            } else {
                Swal.fire({
                    title: 'No se encontró ningún personaje fuerte',
                    text: 'No hay datos disponibles',
                    icon: 'error',
                });
            }
        }

        function obtenerPersonajeMasFuerte() {
            $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajeMasFuerte')}", {
                dataType: "json",
                data: {

                }
            }).done(function (data) {
                console.log(data);
                personajeMasFuerteSwal(data);
            });
        }


    </script>
</body>

</html>