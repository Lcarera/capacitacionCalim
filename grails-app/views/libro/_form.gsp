<label for="titulo">Titulo:</label>
<input type="text" name="titulo" id="titulo" value="${libro?.titulo}">
<br>
<label for="autor">Autor:</label>
<input type="text" name="autor" id="autor" value="${libro?.autor}">
<br>
<button type="submit">Guardar</button>
<g:link controller="libro" action="list">Volver</g:link>