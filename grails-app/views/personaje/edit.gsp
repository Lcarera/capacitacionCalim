<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
     <style>
    .container-right {
    text-align: right;
    }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <g:form controller="personaje" action="update">
        <g:hiddenField name="id" value="${personajeCommand.id}" />
        <g:render template="form" />
        <div class="center-content">
            <div class="container-box container-right">
                <a onclick="borrarSwal()" id="${personajeCommand.id}" href="javascript:;" class="btn btn-danger ">Borrar</a>
                <g:link controller="personaje" class="btn btn-secondary" action="list">Volver</g:link>
                <button class="btn btn-success" onclick="guardarSwal()" type="button" style="background-color: #16b179;">Guardar</button> 
           </div>
        </div>
    </g:form>

    <script>
        function borrarSwal() {
            Swal.fire({
                title: 'Borrar personaje',
                text: 'Esta seguro que desea borrar el personaje?',
                showCancelButton: true,
                confirmButtonText: 'Borrar',
                cancelButtonText: 'Cancelar',
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('Personaje Borrado!', '', 'success')
                    window.location.href = "${createLink(action:'delete',)}/" + '${personajeCommand.id}'
                } else if (result.isDenied) {
                    Swal.fire('Borrado cancelado', '', 'info')
                }
            })
        }


    function guardar() {
      Swal.fire({
  title: 'Confirmation',
  text: 'Do you want to perform this action?',
  icon: 'question',
  showCancelButton: true,
  confirmButtonText: 'OK',
  cancelButtonText: 'Cancel'
}).then((result) => {
  if (result.isConfirmed) {
    // User clicked the "OK" button, perform your action here
    // For example, you can make an AJAX request, update the DOM, etc.
    // You can place your action code here.
    Swal.fire('Action performed', '', 'success');
  } else if (result.isDismissed) {
    // User clicked the "Cancel" button or closed the alert, handle it here if needed
    Swal.fire('Action canceled', '', 'info');
  }
});
    }
    
    async function guardarSwal() {
    const result = await Swal.fire({
        title: 'Confirmar cambios?',
        showDenyButton: true,
        showCancelButton: true,
        closeOnClickOutside: false,
        confirmButtonText: 'Guardar',
        cancelButtonText: 'Cancelar',
    });

    if (result.isConfirmed) {
        Swal.fire('Guardado!', '', 'success');
        // Proceed with your action after user confirmation.
    } else if (result.isDenied) {
        Swal.fire('Guardado Cancelado', '', 'info');
        // Handle the case when the user denies the action.
    }
}

    </script>
    <script src="sweetalert2.all.min.js"></script>
</body>

</html>