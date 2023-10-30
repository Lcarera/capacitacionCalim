<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <g:form controller="libro" action="save">
            <g:render template="form"/>
            <div class="center-content">
                <div class="container-box">                    
                </div>
            </div>
        </g:form>
        <script>
            $(document).ready(function () {
                swal("Bienvenido a crear libros!", "sos re capo makinola");
            });
        </script>
    </body> 
</html>