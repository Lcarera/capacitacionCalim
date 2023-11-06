package capacitacioncalim.personaje

import grails.converters.JSON
import org.joda.time.LocalDate
import capacitacioncalim.Auxiliar

class PersonajeController {

    def personajeService

    def listJson() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def list() {
        def personajes = personajeService.listPersonajes()
        [personajes: personajes]
    }


    def create() {
    }

    def save(PersonajeCommand command) {
        try{
            personajeService.save(command)
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(Exception e) {
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render(view: "create", model : [personajeCommand: command])
        }
    }

    def edit (Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }

    def update(PersonajeCommand command) {
        personajeService.update(command)
        redirect(action:"list")
    }

    def delete(Long id) {
        personajeService.delete(id)
        redirect(action:"list")
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def ajaxGetArmas() {
        def armas = personajeService.listArmas()
        render armas as JSON
    }

    def ajaxGetPersonajeMasFuerte() {
        def personaje = personajeService.getPersonajeMasFuerte()
        render personaje as JSON 
    }

}