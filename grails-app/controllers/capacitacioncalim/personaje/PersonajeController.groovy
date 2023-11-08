package capacitacioncalim.personaje
import capacitacioncalim.arma.Arma
import capacitacioncalim.Auxiliar
import grails.converters.JSON

class PersonajeController{

    def PersonajeService

    def ajaxGetPersonajes() {
        def personajes = personajeService.getPersonajesSQL()
        render personajes as JSON
    }

    def list() {
    }

    def create() {  
    }

    def ajaxGetArmas() {
        def armas = personajeService.listArmas()
        render armas as JSON
    }
    
    def calcularMasPoderoso() {
        def todosLosPersonajes = Personaje.list()
        def personajeMasPoderoso = null
        def maxPuntos = 0

        todosLosPersonajes.each { personaje ->
            def puntosDeFuerza = personaje.puntosFuerza
            def puntosDeAtaque = personaje.arma.puntosAtaque
            def totalPuntos = puntosDeFuerza + puntosDeAtaque

            if (totalPuntos > maxPuntos) {
                maxPuntos = totalPuntos
                personajeMasPoderoso = personaje
            }
        }

        if (personajeMasPoderoso) {
        def mensaje = "El personaje más poderoso es ${personajeMasPoderoso.nombre} con ${maxPuntos} puntos de fuerza y ataque combinados."
        render "Swal.fire('${mensaje}');"
        } else {
        render "Swal.fire('No hay personajes creados.');"
         }
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
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }

    def update(PersonajeCommand command) {
        try{
            personajeService.update(command)
            flash.message = "Personaje actualizado correctamente"
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
            personajeService.delete(id)
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