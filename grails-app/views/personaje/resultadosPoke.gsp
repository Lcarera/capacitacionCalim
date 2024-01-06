<html>
<head>
    <title>Resultados del Pokémon</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h1 class="mb-4">Resultados del Pokémon</h1>

    <p>Opción seleccionada: ${opcion}</p>

    <div class="mb-3">
        <label for="resultado" class="form-label">Resultado:</label>
        <pre>${resultado}</pre>
    </div>

    <a href="${createLink(controller: 'personaje', action: 'buscarPoke')}">Volver a buscar</a>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
