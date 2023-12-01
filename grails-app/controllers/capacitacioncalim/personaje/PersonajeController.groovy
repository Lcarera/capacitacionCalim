package capacitacioncalim.personaje

import capacitacioncalim.Auxiliar
import capacitacioncalim.AccessRulesService
import grails.converters.JSON
import org.joda.time.LocalDate
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PersonajeController {
    def accessRulesService
    def personajeService

    def index() {
        redirect(action: "list")
    }

    def list() {
        render (view: 'list', model: [isAdmin: accessRulesService.isAdmin()])
    }

    @Secured(['ROLE_USER'])
    def create() {}

    def save(PersonajeCommand personajeCommand) {
        try{
            command.fechaCreacion = new LocalDate()
            personajeService.save(personajeCommand)
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            flash.error = e.message.split("finerror")[0]
            Auxiliar.printearError e
            render (view: "create", model: [personajeCommand: command])
        }
        catch(Exception e) {
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "create", model: [personajeCommand: command])
        }
    }

    def edit(Long id) {
        try {
            def personajeCommand = personajeService.getPersonajeCommand(id)
            [personajeCommand: personajeCommand]
        }
        catch (Exception e) {
            flash.error = "No existe un personaje con ese id"
            Auxiliar.printearError e
            render (view: "list")
        }
    }

    def update(PersonajeCommand command) {
        try{
            if (!command.gritoGuerra) {
                command.gritoGuerra = "-"
            }
            
            personajeService.update(command)
            flash.message = "Personaje editado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            flash.error = e.message.split("finerror")[0]
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al editar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def delete(Long id) {
        try {
            flash.message = "Personaje eliminado correctamente"
            personajeService.delete(id)
            redirect(action: "list")
        }
        catch(AssertionError e) {
            flash.error = e.message.split("finerror")[0]
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al eliminar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    @Secured(['ROLE_ADMIN'])
    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    @Secured(['ROLE_USER'])
    def ajaxGetPersonajesByCurrentUser() {
        def currentUser = accessRulesService.getCurrentUser()
        def personajes = personajeService.listPersonajesByUser(currentUser.id)
        render personajes as JSON
    }
}