package capacitacioncalim.personaje

import grails.converters.JSON

import capacitacioncalim.Auxiliar

import capacitacioncalim.User

import capacitacioncalim.AccessRulesService
import grails.plugin.springsecurity.annotation.Secured

class PersonajeController {
    def accessRulesService
    def personajeService

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def list() {
        User user = accessRulesService.getCurrentUser()

        if ( accessRulesService.isAdmin() ) {
            def personajes = personajeService.listPersonajes()
            [personajes: personajes]
        }
        else{
            def personajes = personajeService.listPersonajesUsuario(user)
            [personajes: personajes]
        }
    }

    @Secured(['ROLE_USER'])
    def create() {  
    }

    @Secured(['ROLE_ADMIN'])
    def hola(){
        render("hola")
    }

    @Secured(['ROLE_USER'])
    def save(PersonajeCommand personajeCommand) {
        try{
            User user = accessRulesService.getCurrentUser()
            personajeCommand.userId = user.id
            personajeService.save(personajeCommand)
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "create", model: [personajeCommand: personajeCommand])
        }
        catch(Exception e){
            flash.error = "Error al guardar el Personaje"
            Auxiliar.printearError e
            render (view: "create", model: [personajeCommand: personajeCommand])
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
            flash.message = "Personaje guardado correctamente"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el Personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    
    }

    def delete(Long id) {
        try{
            personajeService.delete(id)
            redirect(action: "list")
            flash.message = "Personaje borrado correctamente"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al borrar el Personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def ajaxGetArmas()
    {
        def armas = personajeService.listArmas()
        render armas as JSON
    }

    def ajaxGetPersonajePoderoso()
    {
        def personaje = personajeService.getMasPoderoso()
        render personaje as JSON

    }

}