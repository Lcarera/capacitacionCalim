<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="layout" content="main">
</head>

<body>
    <g:form controller="selenium" action="ajaxGetPokeAPIData">
        <label for="busqueda">¿De cuál pokemón quieres obtener información?:</label>
        <input type="text" id="busqueda" name="busqueda" /><br /><br />

        <label for="buscarPor">¿Qué información del pokemón quieres ver?</label>
        <select name="buscarPor" id="buscarPor">
            <option value="sprite">Sprite de frente</option>
            <option value="evolucion">Nombre de la evolución</option>
            <option value="habilidades">Habilidades</option>
            <option value="todas">Todas las opciones</option>
        </select><br /><br />

        <input id="botonSubmit" type="submit" value="Buscar" />
    </g:form>
    <div id="divPokemonData" style="display: none;">
        <img src="" alt="imagen del pokemon buscado">
        <ul></ul>
        <p></p>
    </div>
    <p id="pError" style="display: none;"></p>

    <script>
        const formulario = document.getElementsByTagName("form")[0];
        const botonSubmit = document.getElementById("botonSubmit");

        botonSubmit.addEventListener("click", async (e) => {
            e.preventDefault();
            
            const respuesta = await fetch('${createLink(controller:"selenium", action:"ajaxGetPokeAPIData")}', {
                method: 'POST',
                body: new FormData(formulario)
            })
            .then(response => {
                return response.json()
            })
            .catch(error => {
                console.log(error);
            });

            const pError = document.getElementById("pError");
            pError.style.display = respuesta["error"] ? "block" : "none";

            const divPokemonData = document.getElementById("divPokemonData");
            divPokemonData.style.display = respuesta && !respuesta["error"] ? "block" : "none";
            if (respuesta && !respuesta["error"]) {
                const imgSprite = divPokemonData.getElementsByTagName("img")[0];
                imgSprite.style.display = respuesta["sprite"] ? "block" : "none";
                if (respuesta["sprite"]) {
                    imgSprite.src = respuesta["sprite"]
                }

                const ulHabilidades = divPokemonData.getElementsByTagName("ul")[0];
                ulHabilidades.style.display = respuesta["habilidades"] ? "block" : "none";
                ulHabilidades.innerHTML = "";
                if (respuesta["habilidades"]) {
                    respuesta["habilidades"].forEach(habilidad => {
                        const liHabilidad = document.createElement("li");
                        liHabilidad.innerText = habilidad;

                        ulHabilidades.appendChild(liHabilidad);
                    });
                }

                const pEvolucion = divPokemonData.getElementsByTagName("p")[0];
                pEvolucion.style.display = respuesta["evolucion"] ? "block" : "none";
                if (respuesta["evolucion"]) {
                    pEvolucion.innerText = respuesta["evolucion"];
                }
            } else {
                pError.innerText = respuesta["error"];
            }
        })
    </script>
</body>

</html>