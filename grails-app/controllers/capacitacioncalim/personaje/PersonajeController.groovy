package capacitacioncalim.personaje

import capacitacioncalim.Auxiliar
import grails.converters.JSON
import org.joda.time.LocalDate

class PersonajeController {
    def personajeService

    def list() {
        def personajes = personajeService.listPersonajes()
        [personajes: personajes]
    }

    def create() {
    }

    def save(PersonajeCommand command) {
        try {
            command.fechaCreacion = new LocalDate()
            if (!command.gritoGuerra) {
                command.gritoGuerra = "-"
            }

            personajeService.save(command)
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
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
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
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
            personajeService.delete(id)
            flash.message = "Personaje eliminado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            redirect(action: "edit", params: ["id": id])
        }
        catch(Exception e) {
            flash.error = "Error al eliminar el personaje"
            Auxiliar.printearError e
            redirect(action: "edit", params: ["id": id])
        }
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def ajaxGetPersonajeMasFuerte() {
        def personaje = personajeService.getPersonajeMasFuerte()
        render personaje as JSON
    }
}