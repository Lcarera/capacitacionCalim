<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="editorial" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">                    
                    <g:link controller="editorial" class="btn btn-secondary" action="list">Volver</g:link>
                    <button class="btn btn-success" type="submit">Guardar</button>
                </div>
            </div> 
        </g:form>
    </body> 
    <script>
        $(document).ready(function () {
            swal("Bienvenido a crear editoriales!", "sos re capo makinola");
        });
    </script>
</html>

