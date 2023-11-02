package capacitacioncalim.personaje
import capacitacioncalim.arma.Arma
import capacitacioncalim.Auxiliar
import grails.converters.JSON

class PersonajeController{

    def PersonajeService

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes() 
        render personajes as JSON
    }

    def list() {
        def personajes = personajeService.listPersonajes()
        [personajes: personajes]
    }

    def create() {  
    }

    def ajaxGetArmas() {
        def armas = personajeService.listArmas()
        render armas as JSON
    }

    def agrearArco(){
        def arma = new Arma()
        arma.nombre = "Arco"
        arma.puntosAtaque = 12
        personajeService.saveArma(arma)
    }
    def agrearEspada(){
        def arma = new Arma()
        arma.nombre = "Espada"
        arma.puntosAtaque = 20
        personajeService.saveArma(arma)
    }
    def agrearMartillo(){
        def arma = new Arma()
        arma.nombre = "Martillo"
        arma.puntosAtaque = 24
        personajeService.saveArma(arma)
    }


    def save(PersonajeCommand command) {
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

    def edit(Long id) {
        def personajeCommand = personajeCommand.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }

    def update(PersonajeCommand command) {
        try{
            personajeService.update(command)
            flash.message = "Personaje actuelizado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al actualizar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def delete(Long id) {
        try{
            personajeService.delate(id)
            flash.message = "personaje eliminado correctamente"
            redirect(action: "list")
        }
        catch(Exception e){
            flash.error = "Error al eliminar el personaje"
            Auxiliar.printearError e
            render (view: "list", model: [personajeCommand: command])
        }
    }

}