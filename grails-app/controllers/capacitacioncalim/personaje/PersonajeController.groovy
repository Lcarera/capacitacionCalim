package capacitacioncalim.personaje
import grails.converters.JSON
import capacitacioncalim.Auxiliar

class PersonajeController {

    def personajeService

    def listJson() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def list() {
        // def personajes = personajeService.listLibros()
        def personajes = personajeService.dataBaseQueryPersonajes()
        [personajes: personajes]
    }

    def create() {  
    }

    def save(PersonajeCommand command) {
        if (command.puntosSalud < 1) {
            flash.error = "Los puntos de salud del personaje no pueden ser menor que 1!"
            render(view: "create", model: [personajeCommand: command])
        } else {
            if (command.puntosFuerza < 1) {
                flash.error = "Los puntos de fuerza del personaje no pueden ser menor que 1!"
                render(view: "create", model: [personajeCommand: command])
            } else {
                try{
                    personajeService.save(command)
                    flash.message = "Personaje guardado correctamente"
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
        }
    }

    def edit(Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }

    def update(PersonajeCommand command) {
        println("ControllerPersonajeCommand: " + command)
        if (command.puntosSalud < 1) {
            flash.error = "Los puntos de salud del personaje no pueden ser menor que 1!"
            render(view: "edit", model: [personajeCommand: command])
        } else {
            if (command.puntosFuerza < 1) {
                flash.error = "Los puntos de fuerza del personaje no pueden ser menor que 1!"
                render(view: "edit", model: [personajeCommand: command])
            } else {
                try{
                    personajeService.update(command)
                    flash.message = "Personaje guardado correctamente"
                    redirect(action: "list")
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
        }
    }

    def delete(Long id) {
        personajeService.delete(id)
        redirect(action: "list")
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.dataBaseQueryPersonajes()
        render personajes as JSON
    }

    def ajaxGetArmas() {
        def armas = personajeService.dataBaseQueryArmas()
        println("armas: " + armas)
        render armas as JSON
    }
}