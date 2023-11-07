package capacitacioncalim.personaje
import grails.converters.JSON
import capacitacioncalim.Auxiliar
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap


class PersonajeController {
    def sessionFactory
    def personajeService

    def list() {
        def personajes = personajeService.listPersonajes()
        [personajes: personajes]
    }

    def create(){

    }
    
    def save(PersonajeCommand command){
        try{
        personajeService.save(command)
        flash.message = "Personaje guardado correctamente"
        redirect(action:"list")
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

    def update(PersonajeCommand command){
        try{
        personajeService.update(command)
        flash.message = "Personaje editado correctamente"
        redirect(action:"list")
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

    def delete(Long id){
        try{
        personajeService.delete(id)
        flash.message = "Personaje borrado correctamente"
        redirect(action:"list")
        }
        catch(Exception e){
            flash.error = "Error al borrar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def ajaxGetArmas(){
        def armas = personajeService.listArmas()
        render armas as JSON
    }

    def ajaxGetPersonajeMasFuerte(){
        def personaje = personajeService.getPersonajeMasFuerte()
        render personaje as JSON
    }

    def ajaxGetPersonajes(){
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }
}
