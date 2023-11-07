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

        h1.personajeTitulo {
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
            <h1 class="personajeTitulo">Personajes</h1>
        </div>
        <div class="dt-responsive table-responsive">
            <g:link controller="personaje" action="create" class="btn btn-primary" style="float: right; margin-left: 10px">
                Agregar Personaje</g:link>
            
                <button id="masPolentaButton" class="btn btn-info" type="button" onclick="mostrarMasFuerte()" style="float: right; margin-left: 10px">
                EL MAS POLENTA</button>

            <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Puntos de Salud</th>
                        <th>Puntos de Fuerza</th>
                        <th>Fecha de Creación</th>
                        <th>Grito de Guerra</th>
                        <th>Arma Id</th>
                        <th>Arma Nombre</th>
                        <th>Nivel de Poder</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
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
                        "mData": "nombre"
                    }, {
                        "aTargets": [1],
                        "mData": "puntosSalud",
                    }, {
                        "aTargets": [2],
                        "mData": "puntosFuerza"
                    }, {
                        "aTargets": [3],
                        "mData": "fechaCreacion"
                    },{
                        "aTargets": [4],
                        "mData": "gritoGuerra"
                    },{
                        "aTargets": [5],
                        "mData": "armaId"
                    },{
                        "aTargets": [6],
                        "mData": "armaNombre"
                    },{
                        "aTargets": [7],
                        "mData": "personajePoder"
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

                    // Verifica si hay más de 1 personaje y muestra el botón
                    if (data.length > 1) {
                        $("#masPolentaButton").show();
                    } else {
                        $("#masPolentaButton").hide();
                    }
                });
            }

            function mostrarMasFuerte() {
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
                    dataType: "json",
                    data: {}
                }).done(function (data) {
                    var personajes = data;

                    if (personajes.length === 0) {
                        Swal.fire('Sin personajes', 'No hay personajes en la tabla.', 'info');
                        return;
                    }

                    personajes.sort(function (a, b) {
                        return b.personajePoder - a.personajePoder;
                    });

                    var personajeMasFuerte = personajes[0];
                    var empates = [];
                    
                    for (var i = 0; i < personajes.length; i++) {
                        if (personajes[i].personajePoder > personajeMasFuerte.personajePoder) {
                            personajeMasFuerte = personajes[i] 
                        }
                    }

                    for (var i = 0; i < personajes.length; i++) {
                        if (personajes[i].personajePoder === personajeMasFuerte.personajePoder) {
                            empates.push(personajes[i]);
                            console.log("hola : " + empates)
                        }
                    }

                    if (empates.length === 1) {
                        Swal.fire('Personaje más fuerte', 'El personaje más fuerte es: ' + personajeMasFuerte.nombre + '<br>' +
                            'Nivel de poder: ' + personajeMasFuerte.personajePoder, 'info');
                    } else {
                        var mensaje = "Hubo un empate, los ganadores son:" + "<br>";

                        for (var x = 0; x < empates.length; x++) {
                            mensaje += "-" + empates[x].nombre + "<br>";
                        }
                        mensaje += "Nivel de poder todos: " + empates[0].personajePoder;
                        Swal.fire('Empate', mensaje , 'info')   
                    }
                });
            }

        </script>
    </div>
</body>

</html>