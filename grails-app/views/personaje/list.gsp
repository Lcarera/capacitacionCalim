<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
            background-color: #61b1b5;
        }
    </style>
</head>

<body>
    <div class="main-body">
        <div class="page-wrapper">
            <div class="page-header card">
                <div class="row align-items-end">
                    <div class="col-lg-8">
                        <div class="page-header-title">
                            <div class="d-inline">
                                <h4>Personajes</h4>
                                <sec:ifAnyGranted roles="ROLE_USER,ROLE_ADMIN">
                                    <span>Lista de personajes del user</span>
                                </sec:ifAnyGranted>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="dt-responsive table-responsive">
            <sec:ifAnyGranted roles="ROLE_USER">
            <g:link controller="personaje" action="create" class="btn btn-success" style="float: right; margin-left: 10px,">
                <b>+</b></g:link>
            </sec:ifAnyGranted>

            <table id="listPersonaje" class="table table-striped table-bordered nowrap" style="cursor:pointer">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Puntos de Fuerza</th>
                        <th>Puntos de Salud</th>
                        <th>Fecha de Creacion</th>
                        <th>Grito de Guerra</th>
                        <th>Arma</th>
                        <th>Poder Total</th>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <th>User id</th>
                        </sec:ifAnyGranted>

                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        <button onclick="masPoderosoSwal()" class="btn btn-secondary ">Quien es el Mas Poderoso</button>

        </div>

        <script>
            var tabla;
            var personajes;
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
                    },{
                        "aTargets": [7],
                        "mData": "user",
                        "visible": '${request.isUserInRole("ROLE_ADMIN")}'
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

                llenarDatoslistersonaje();
            });

            function llenarDatoslistersonaje() {
                tabla.clear().draw();
                $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
                    dataType: "json",
                    data: {
                        
                    }
                }).done(function (data) {
                    personajes = data;
                    console.log(data);
                    tabla.rows.add(data)
                    tabla.draw();
                });
            }
            function masPoderosoSwal() {
                /*
                    $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajePoderoso')}", {
                        dataType: "json",
                        data: {

                        }
                    }).done(function (data) {
                        if (data[0] != null) {
                            Swal.fire({
                                title: 'Personaje mas Poderoso?',
                                html: `<strong> Nombre: </strong>` + data[0].nombre + `<br> <strong> Poder Total: </strong>` + data[0].poderTotal + `<br> <strong> Arma Elegida: </strong>` + data[0].arma, 
                                confirmButtonText: 'Cerrar',
                            });
                        }
                        else {
                        Swal.fire({
                            title: 'Sin datos',
                            text: 'No hay datos en la tabla para mostrar.',
                            confirmButtonText: 'Cerrar',
                        });
                        }
                    });
                    */
                   if (personajes.length > 0)
                   {
                      for (var i = 0; i < personajes.length; i++) { 
  
                        // Last i elements are already in place   
                        for (var j = 0; j < (personajes.length - i - 1); j++) { 

                            // Checking if the item at present iteration  
                            // is greater than the next iteration 
                            if (personajes[j] > personajes[j + 1]) { 

                                // If the condition is true 
                                // then swap them 
                                var temp = personajes[j] 
                                personajes[j] = personajes[j + 1] 
                                personajes[j + 1] = temp 
                            } 
                        }
                    }
                    Swal.fire({
                        title: 'Personaje mas Poderoso:',
                        html: `<strong> Nombre: </strong>` + personajes[0].nombre + `<br> <strong> Poder Total: </strong>` + personajes[0].poderTotal + `<br> <strong> Arma Elegida: </strong>` + personajes[0].arma, 
                        confirmButtonText: 'Cerrar',
                        });
                   }
                   else
                   {
                        Swal.fire({
                            title: 'Sin datos',
                            text: 'No hay datos en la tabla para mostrar.',
                            confirmButtonText: 'Cerrar',
                        });
                   }
                
                }
        </script>
    </div>
</body>

</html>