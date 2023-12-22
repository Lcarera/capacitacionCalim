package capacitacioncalim.personaje

import capacitacioncalim.Auxiliar

import grails.converters.JSON
 
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PersonajeController {

    def personajeService
    SpringSecurityService springSecurityService

    def index() {
        render (view: 'list')
    }

    def list() {}

    @Secured(['ROLE_USER'])
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
        if (SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')) {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
        } else if (SpringSecurityUtils.ifAllGranted('ROLE_USER')) {
        def usuarioActual = springSecurityService.currentUser
        def personajes = personajeService.listPersonajesFilter(usuarioActual.id)
        render personajes as JSON}
    }

    def ajaxGetPersonajeMasPoderoso() {
        def personaje = personajeService.getPersonajeMasPoderoso()
        render personaje as JSON
    }

}