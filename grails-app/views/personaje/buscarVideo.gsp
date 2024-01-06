<html>
<head>
    <title>Busca video</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h1 class="mb-4">Busca video</h1>

    <form action="${createLink(controller: 'personaje', action: 'buscarVideo')}" method="post">
        <div class="mb-3">
            <label for="tituloVideo" class="form-label">Introducir título de video</label>
            <div class="input-group">
                <input type="text" class="form-control" id="tituloVideo" name="tituloVideo" aria-describedby="emailHelp">
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">Buscar</button>
                </div>
            </div>
        </div>
    </form>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>