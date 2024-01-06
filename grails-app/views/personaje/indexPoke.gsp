<html>
<head>
    <title>Busca Poke</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h1 class="mb-4">Index Poke</h1>

    <form action="${createLink(controller: 'personaje', action: 'buscarPoke')}" method="post">
        <div class="mb-3">
            <label for="nombrePoke" class="form-label">Introducir el nombre del pokemon</label>
            <div class="input-group">
                <input type="text" class="form-control" id="nombrePoke" name="nombrePoke">
                <select class="form-control" id="opcion" name="opcion">
                    <option value="frente">Sprite de Frente</option>
                    <option value="evolucion">Nombre de la Evolución</option>
                    <option value="habilidades">Habilidades</option>
                    <option value="todas">Todas las Opciones</option>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">Buscar</button>
                </div>
            </div>
        </div>
    </form>

    <div class="alert alert-info" role="alert">
        Opción: ${flash.opcion}
        <br/>
        Nombre del Pokémon: ${flash.nombrePoke}
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
