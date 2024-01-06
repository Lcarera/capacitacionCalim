package capacitacioncalim.personaje

import capacitacioncalim.Auxiliar

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper
import capacitacioncalim.selenium.SeleniumService
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions


import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PersonajeController {

    def personajeService
    def seleniumService

    def index() {
        render (view: 'list')
    }

    def buscar(){}

    def indexPoke(){}

    /*def buscarPoke() {
    String opcion = params.opcion
    String nombrePoke = params.nombrePoke
    println(nombrePoke)
    println(opcion)

    flash.opcion = opcion
    flash.nombrePoke = nombrePoke


    redirect(action: 'indexPoke')
    } */  

    def buscarPoke() {
    String nombrePoke = params.nombrePoke
    String opcion = params.opcion

    if (nombrePoke) {
        def url = "https://pokeapi.co/api/v2/pokemon/$nombrePoke/"
        println "URL: $url"

        try {
            def rest = new RestBuilder()
            def resp = rest.get(url)
            println "Código de Estado: ${resp.status}"

            def pokemonData = resp.getJson()
            
            def resultado
            switch (opcion) {
                case "frente":
                    resultado = pokemonData.sprites.front_default
                    break
                case "evolucion":
                    resultado = "nombre evolucion"
                    break
                case "habilidades":
                    resultado = pokemonData.abilities.collect { it.ability.name }
                    break
                case "todas":
                    resultado = pokemonData
                    break
                default:
                    resultado = "opcion invalida"
                    break
            }

            // Renderiza la vista con los resultados
            render view: "resultadosPoke", model: [opcion: opcion, resultado: resultado]
        } catch (Exception e) {
            // Maneja el caso de error
            render status: 500, text: "Error al obtener información del Pokémon: ${e.message}"
        }
    } else {
        render status: 400, text: "Por favor, proporciona un nombre de Pokémon"
    }
}





    def buscarVideo(){

        String tituloVideo = params.tituloVideo
        WebDriver driver = seleniumService.inicializarDriver("")

        try {

        driver.get("https://www.youtube.com/watch?v=3a2pLEfCERI")

        /*driver.findElement(By.name("search_query")).sendKeys(tituloVideo)
        driver.findElement(By.id("search-icon-legacy")).click()

        Thread.sleep(5000)  

        println("lolo")
        try {
        WebElement videos = driver.findElements(By.id("video-title"))
        videos.click()
        } catch (Exception e) {
            e.printStackTrace()
        }


        println(video)
        println("lala")
        
        println(video)
        */
        Thread.sleep(5000)  

        String titulo = driver.findElement(By.xpath('//html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[1]/h1/yt-formatted-string')).getText()

        String likes = driver.findElement(By.xpath('/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[2]/div[2]/div/div/ytd-menu-renderer/div[1]/segmented-like-dislike-button-view-model/yt-smartimation/div/div/like-button-view-model/toggle-button-view-model/button/div[2]')).getText()

        String visualizaciones = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[4]/div[1]/div/ytd-watch-info-text/div/yt-formatted-string/span[1]")).getText()
        //String comentarios = driver.findElement(By.xpath('/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-comments/ytd-item-section-renderer/div[1]/ytd-comments-header-renderer/div[1]/h2/yt-formatted-string/span[1]')).getText()

        // Imprime los resultados
        println(likes)
        println(visualizaciones)
        //println(comentarios)

        params.titulo = titulo
        params.likes = likes
        params.visualizaciones = visualizaciones

        render(view: 'resultado')
    } catch (Exception e) {
        // Si hay algún error, imprime un mensaje indicando que el video no se encontró
        println(e.message)
        println("El video no se encuentra.")
    } finally {
        // Cierra el navegador
        driver.quit()
    }
    }

    def list() {}

    def create() {}

    def save(PersonajeCommand personajeCommand) {
        try{
            personajeService.save(personajeCommand)
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            flash.message = e.message.split("finerror")[0]
            Auxiliar.printearError e
            render(view: "create", model: [personajeCommand: personajeCommand])
        }
        catch(Exception e){
            flash.message = "Error al guardar el personaje"
            Auxiliar.printearError e
            render(view: "create", model: [personajeCommand: personajeCommand])
        }
    }

    def edit(Long id) {
        [personajeCommand: personajeService.getPersonajeCommand(id)]
    }

    def update(PersonajeCommand command) {
        try{
            personajeService.update(command)
            redirect(action: "list")
            flash.message = "Personaje guardado"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    
    }

    def delete(Long id) {
        try{
            personajeService.delete(id)
            redirect(action: "list")
            flash.message = "Personaje borrado"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def ajaxGetPersonajeMasPoderoso() {
        def personaje = personajeService.getPersonajeMasPoderoso()
        render personaje as JSON
    }

}