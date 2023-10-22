<label for="nombre">Nombre:</label>
<input type="text" name="nombre" id="nombre" value="${editorial?.nombre}">
<br>
<label for="direccion">Direccion:</label>
<input type="text" name="direccion" id="direccion" value="${editorial?.direccion}">
<br>
<label for="anocreacion">Ano creacion:</label>
<input type="number" name="anocreacion" id="anocreacion" value="${editorial?.anocreacion}">
<button type="submit">Guardar</button>
<g:link controller="editorial" action="list">Volver</g:link>
