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

            h1.personajetitulo {
                text-align: center;
                color: black;
            }
            .BotonCalimVerde {
                background-color: #c1d64a;
                font-size: 30px;
                font-weight: bold;
                height: 35.5px;
                color: white;
                text-align: justify;   
                margin-left: 10px;
                padding-top: 0px;
                font-family: "Lucida Console", "Courier New", monospace;              
            }
            .BotonCalimCeleste{
                background-color: #2091a2;
                color: white;
            }
            .FondoBlancoPersonajes{
                box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3);
                padding: 20px;
                border-radius: 10px;
            }
        </style>
    </head>

    <body>
        <div class="container col-10">
            <div class="container col-12 xd">
                <h1 class="personajetitulo">PERSONAJES</h1>
            </div>
            <br>
            <div class="dt-responsive table-responsive FondoBlancoPersonajes">
                <div class="paraprobar">
                <g:link controller="personaje" action="create" class="btn BotonCalimVerde" style="float: right">+</g:link>
                </div>
                <table id="listPersonaje" class="table table-striped table-hover table-bordered nowrap" style="cursor:pointer">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Puntos de Salud</th>
                            <th>Puntos de Fuerza</th>
                            <th>Grito de Guerra</th>
                            <th>Arma</th>
                            <th>Fecha de Creacion</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <button onclick="personajeMasFuerte()" href="javascript:;" class="btn BotonCalimCeleste">Personaje Mas Fuerte</button>
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
                        },{
                            "aTargets": [4],
                            "mData": "arma"
                        }, {
                            "aTargets": [5],
                            "mData": "fechaCreacion"
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

            
            </script>
            <script>
            function personajeMasFuerte() {
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajeMasFuerte')}", {
                    dataType: "json",
                    data: {}
                }).done(function (data) {
                    console.log(data);
                    if (data) {
                        mostrarModalPersonajeMasFuerte(data);
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'No se pudo obtener el personaje más fuerte.',
                        });
                    }
                });
            }


            function mostrarModalPersonajeMasFuerte(data) {
                const MasFuerte = data[0]; 
                Swal.fire({
                    title: '<span style="text-transform:uppercase">EL MÁS FUERTE</span>',
                    html: 'El personaje más fuerte es <b>' + MasFuerte.nombre + '</b> <br>' +
                    'Tiene una fuerza de <b>' + MasFuerte.puntosFuerza + '</b><br>' +
                    'Su arma es <b>' + MasFuerte.arma + '</b>',
                });
            } 
        </script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>