<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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

            h1 {
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
                <h1>PERSONAJES</h1>
            </div>
            <div class="dt-responsive table-responsive">
                <button id="btnPersonajeMasFuerte" class="btn btn-primary" style="float: right; margin-left: 10px">Ver personaje más fuerte</button>
                <g:link controller="personaje" action="create" class="btn btn-primary" style="float: right; margin-left: 10px">Agregar Personaje</g:link>
                <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                    <thead>
                        <tr>
                            <th>Fecha de creación</th>
                            <th>Nombre</th>
                            <th>Ptos. de salud</th>
                            <th>Ptos. de fuerza</th>
                            <th>Arma</th>
                            <th>Ptos. de ataque totales</th>
                            <th>Grito de guerra</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <script>
                let personajes;
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
                    dataType: "json",
                    data: {
                    }
                }).done(function (data) {
                    personajes = data;
                });

                var tabla;
                jQuery(document).ready(function () {
                    tabla = $('#listPersonaje').DataTable({
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
                        aaSorting: [
                            [0, 'desc']
                        ],
                        aoColumnDefs: [{
                            "aTargets": [0],
                            "mData": "fechaCreacion"
                        }, {
                            "aTargets": [1],
                            "mData": "nombre",
                        }, {
                            "aTargets": [2],
                            "mData": "puntosSalud"
                        }, {
                            "aTargets": [3],
                            "mData": "puntosFuerza"
                        }, {
                            "aTargets": [4],
                            "mData": "arma.nombre"
                        }, {
                            "aTargets": [5],
                            "mData": "puntosAtaqueTotal"
                        }, {
                            "aTargets": [6],
                            "mData": "gritoGuerra"
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

                    $("#btnPersonajeMasFuerte").click(() => {
                        let title;
                        let text;
                        
                        if (personajes) {
                            let personajesMasFuertes = [ { puntosAtaqueTotal: 0 } ];
                            personajes.map(personaje => {
                                if (personaje.puntosAtaqueTotal > personajesMasFuertes[0].puntosAtaqueTotal) {
                                    personajesMasFuertes = [];
                                    personajesMasFuertes.push(personaje);
                                } else if (personaje.puntosAtaqueTotal === personajesMasFuertes[0].puntosAtaqueTotal) {
                                    personajesMasFuertes.push(personaje);
                                }
                            });
                            console.log(personajesMasFuertes);
                            if (personajesMasFuertes.length == 1) {
                                const title = "¿Quién es el más fuerte?";
                                const text = "El personaje más fuerte es:<br>"
                                + personajesMasFuertes[0].nombre + ", ataque: " + personajesMasFuertes[0].puntosAtaqueTotal;
                            } else {
                                const title = "¿Quiénes son los más fuertes?";
                                const text = "El personaje más fuerte es:<br>";
                                personajesMasFuertes.map(personaje => {
                                    text += personaje.nombre + ", ataque: " + personaje.puntosAtaqueTotal;
                                })
                            }
                        } else {
                            const title = "No hay personajes";
                            const text = "Para ver cuál es el personaje más fuerte, primero ingresa unos cuantos.";
                        }

                        swal({
                            title: title,
                            text: text,
                            button: "Cerrar"
                        })

                    });
                });

                function llenarDatoslistPersonaje() {
                    tabla.clear().draw();
                    tabla.rows.add(personajes)
                    tabla.draw();
                }
            </script>
        </div>
    </body>
</html>