package capacitacioncalim.personaje

import grails.converters.JSON

import capacitacioncalim.Auxiliar

class PersonajeController {

    def personajeService

    def prueba() {
        render "Hola mundo"
    }

    def listJson() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def list() {
        def personaje = personajeService.listPersonajes()
        [personajes: personaje]
    }

    def create() {  
    }

    def save(PersonajeCommand command) {
        try{
            personajeService.save(command)
            flash.message = "Personaje creado"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "create", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "create", model: [personajeCommand: command])
        }
    }

    def edit(Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
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

    def ajaxGetArmas() {
        def armas = personajeService.listArmas()
        render armas as JSON
    }


}