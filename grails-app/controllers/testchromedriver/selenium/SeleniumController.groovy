package testchromedriver.selenium

import grails.converters.JSON
import groovy.json.JsonException
import groovy.json.JsonSlurper

import java.util.regex.Matcher
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class SeleniumController {
    SeleniumService seleniumService
    WebDriver driver

    def buscarEnVideo() {
        render (view: 'buscarEnVideo')
    }

    def buscarEnPokeAPI() {
        render (view: 'buscarEnPokeAPI')
    }

    def ajaxGetVideoData(SeleniumCommand command) {
        driver = seleniumService.inicializarDriver("")
        String searchQuery = command.busqueda.replace(" ", "+")
        String url = "https://www.youtube.com/results?search_query=" + searchQuery
        String resultado

        try {
            driver.get(url)

            // Navegamos hasta el primer video que resulta del query.
            WebElement primerVideo = driver.findElement(By.cssSelector("#contents ytd-video-renderer"))
            WebElement thumbnail = primerVideo.findElement(By.cssSelector("#thumbnail"))
            String urlVideo = thumbnail.getAttribute("href")
            driver.get(urlVideo)
            
            // A partir del url del video de YT obtenemos la información dependiendo de la propiedad "buscarPor" del command.
            WebDriverWait wait = new WebDriverWait(driver, 10)

            switch (command.buscarPor) {
                case 'descripcion':
                    // Buscamos el contenedor de la descripcion y lo clickeamos para acceder al texto contenido.
                    WebElement botonExpandirDescripcion = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#expand")))
                    botonExpandirDescripcion.click()

                    WebElement divDescripcionTexto = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#description-inline-expander > yt-attributed-string")))
                    resultado = divDescripcionTexto.getText()
                    break
                case 'likes':
                    WebElement botonLikes = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#top-level-buttons-computed like-button-view-model button")))
                    String ariaLabelBotonLikes = botonLikes.getAttribute("aria-label")

                    String patron = /al igual que otras (\d+(,\d+)*) personas/
                    Matcher matcher = (ariaLabelBotonLikes =~ patron)
                    matcher.find()
                    resultado = matcher.group(1)
                    break
                case 'comentarios':
                    JavascriptExecutor js = (JavascriptExecutor) driver
                    int scroll = 50

                    // Como el contenedor de comentarios solo se renderiza cuando scrolleamos hasta cierta profundidad, debemos programar al driver para que haga eso con ayuda del JavascriptExecutor.
                    while (true) {
                        try {
                            WebElement spanComentarios = driver.findElement(By.cssSelector("#count yt-formatted-string span:nth-child(1)"))

                            resultado = spanComentarios.getText()
                            break
                        } catch (Exception e) {
                            try {
                                js.executeScript("window.scrollTo(0, " + scroll + ");")
                                scroll += 50
                                Thread.sleep(250)
                            } catch (InterruptedException ex) {
                                ex.printStackTrace()
                            }
                        }
                    }

                    break
            }
        } finally {
            driver.quit()
            render text: resultado
        }
    }

    private boolean validatePokeAPICommand(SeleniumCommand command) {
        return command.buscarPor && command.busqueda && command.busqueda =~ /^[a-zA-Z ]+$/ && ['sprite', 'evolucion', 'habilidades', 'todas'].contains(command.buscarPor)
    }

    def ajaxGetPokeAPIData(SeleniumCommand command) {
        LinkedHashMap<String, Object> resultado = []

        if (!validatePokeAPICommand(command)) {
            resultado["error"] = "La informacion solicitada es invalida"
        } else {
            String nombrePokemon = command.busqueda
            driver = seleniumService.inicializarDriver("")

            try {
                if (command.buscarPor == "evolucion") {
                    resultado["evolucion"] = getEvolucion(nombrePokemon)
                } else {
                    String apiUrl = "https://pokeapi.co/api/v2/pokemon/$nombrePokemon"
                    driver.get(apiUrl)

                    String dataPokemonString = driver.findElement(By.tagName("body")).getText()
                    LinkedHashMap<String, Object> dataPokemon = new JsonSlurper().parseText(dataPokemonString) as LinkedHashMap<String, Object>

                    switch(command.buscarPor) {
                        case "sprite":
                            resultado["sprite"] = getSprite(dataPokemon)
                            break
                        case "habilidades":
                            resultado["habilidades"] = getHabilidades(dataPokemon)
                            break
                        case "todas":
                            resultado = getTodas(dataPokemon)
                            break
                    }
                }
            }
            catch (JsonException e) {
                resultado["error"] = "No se ha encontrado un pokemon con ese nombre"
            }
            catch (Exception e) {
                resultado["error"] = "La informacion solicitada es invalida"
            }
            finally {
                driver.quit()
            }
        }

        render resultado as JSON
    }

    private String getSprite(LinkedHashMap<String, Object> dataPokemon) {
        return dataPokemon.sprites.front_default
    }

    private String getEvolucion(String nombrePokemon) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon-species/$nombrePokemon"
        driver.get(apiUrl)
        
        String dataPokemonSpeciesString = driver.findElement(By.tagName("body")).getText()
        LinkedHashMap<String, Object> speciesData = new JsonSlurper().parseText(dataPokemonSpeciesString) as LinkedHashMap<String, Object>

        String evolutionChainUrl = speciesData.evolution_chain.url
        driver.get(evolutionChainUrl)

        String dataEvolutionChainString = driver.findElement(By.tagName("body")).getText()
        LinkedHashMap<String, Object> evolutionChainData = new JsonSlurper().parseText(dataEvolutionChainString) as LinkedHashMap<String, Object>

        LinkedHashMap<String, Object> chain = evolutionChainData.chain as LinkedHashMap<String, Object>
        String pokemonNombreChain = chain.species.name

        while (pokemonNombreChain != nombrePokemon && chain.evolves_to.size() > 0) {
            chain = chain.evolves_to[0]
            pokemonNombreChain = chain.species.name
        }

        if (pokemonNombreChain == nombrePokemon && chain.evolves_to.size() > 0) {
            return chain.evolves_to[0].species.name
        } else {
            return "No se encontró la siguiente evolución para $nombrePokemon"
        }
    }

    private String[] getHabilidades(LinkedHashMap<String, Object> dataPokemon) {
        def habilidades = dataPokemon.abilities.collect { it.ability.name }
        return habilidades
    }

    private LinkedHashMap<String, Object> getTodas(LinkedHashMap<String, Object> dataPokemon) {
        LinkedHashMap<String, Object> resultado = []
        resultado["sprite"] = getSprite(dataPokemon)
        resultado["habilidades"] = getHabilidades(dataPokemon)
        resultado["evolucion"] = getEvolucion(dataPokemon.name)

        return resultado
    }
}