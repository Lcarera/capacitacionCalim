<!DOCTYPE html>
<html lang="en">
<head>
    <div class="theme-loader" id="loaderGrande">
        <div class="ball-scale">
            <div class="contain">
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
                <div class="ring"><div class="frame"></div></div>
            </div>
        </div>
    </div>
    <meta name="layout" content="main" />
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
                                <sec:ifAnyGranted roles="ROLE_USER">
                                    <span>Lista de personajes del user</span>
                                </sec:ifAnyGranted>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-body">
            <div class="card">
                <div class="card-block">
                    <div class="dt-responsive table-responsive">
                        <div style="float: right;margin-left: 1em;">
                            <button class="btn btn-success"  onclick="mostrarMasPoderoso()">Mostrar mas poderoso</button>
                            <g:link controller="personaje" action="create" class="btn btn-primary">Agregar Personaje</g:link>
                        </div>
                        <table id="listPersonajes" class="table table-striped table-bordered nowrap" style="cursor: pointer">
                            <thead>
                                <tr>
                                <th>Nombre</th>
                                <th>Puntos de Fueza</th>
                                <th>Puntos de Salud</th>
                                <th>Grito de Guerra</th>
                                <th>Arma</th>
                                <th>Fecha de Creación</th>
                                <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <th>User</th>
                        </sec:ifAnyGranted>

                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
    var tabla;
    var isAdmin;
//     $.ajax("${createLink(controller:'personaje', action:'ajaxEsAdmin')}", {
//     dataType: "json",
//     data: {}
// }).done(function (data) {
//     isAdmin = data.isAdmin;
// })
    jQuery(document).ready(function () {
        tabla = $('#listPersonajes').DataTable({
            //bAutoWidth: false,
            //bSortCellsTop: true,
            //BProcessing: true,
            "ordering": true,
            "searching": true,
            oLanguage: {
                sProcessing: "Buscando...",
                sSearch: "",
                sLengthMenu: "_MENU_",
                sZeroRecords: "Buscando personaje(s)...",
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
                "mData": "puntosFuerza",
            }, {
                "aTargets": [2],
                "mData": "puntosSalud"
            }, {
                "aTargets": [3],
                "mData": "gritoGuerra",
                "mRender": function (data, type, full) {
                    return data ? data : '-';
                }
            },{
                "aTargets": [4],
                "mData": "arma"
            },{
                "aTargets": [5],
                "mData": "fechaCreacion"
            },{
                "aTargets": [6],
                "mData": "user",
                "visible": <%= request.isUserInRole("ROLE_ADMIN") %>
            }
            
        ],
            buttons: [],
            sPaginationType: 'simple',
            sDom: '<"row"<"col-4"l><"col-8"Bf>>t<"row"<"col-6"i><"col-6"p>>',
            fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                $(nRow).off('click').on('click', function () {
                    window.location.href = ('${createLink(controller:"personaje", action:"edit")}') + '/' + aData['id'];
                });
            }
        });

        llenarDatosListPersonaje();
    });
    function llenarDatosListPersonaje() {
        tabla.clear().draw();
        $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajes')}", {
            dataType: "json",
            data: {
            }
        }).done(function (data) {
            $("#loaderGrande").fadeOut("slow");
            tabla.rows.add(data)
            tabla.draw();
        });
    }

    

    function mostrarMasPoderoso() {
        $.ajax("${createLink(controller:'personaje', action:'ajaxGetPersonajeMasPoderoso')}", {
            dataType: "json",
            data: {}
        }).done(function (data) {
            if (data.length === 0) {
                Swal.fire({
                    title: 'Sin personajes!',
                    icon: 'warning',
                    text: 'No hay personajes!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#2091A2',
                });
            } else {
                Swal.fire({
                    title: 'Personaje mas Poderoso',
                    icon: 'info',
                    text: 'El personaje mas poderoso es ' + data[0].nombre + ', con un poder total de ' + data[0].poderTotal + ' puntos.',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#2091A2',

                });
            }
        });
    }
</script>
</body>
</html>
