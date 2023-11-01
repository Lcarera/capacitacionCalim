package capacitacioncalim.libro
import grails.converters.JSON
import capacitacioncalim.Auxiliar
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap


class PersonajeController {
    def sessionFactory
    def personajeService

    def listJson() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def create() {
    }
    def save(PersonajeCommand command){
        personajeService.save(command)
        flash.message("Personaje Creado!")
        redirect(action:"list")
    }
    def edit(Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }
    def update(PersonajeCommand command){
        personajeService.update(command)
        flash.message("Personaje Actualizado!")
        redirect(action:"list")
    }
    def delete(PersonajeCommand command){
        personajeService.delete(command)
        flash.message("Personaje Eliminado!")
        redirect(action:"list")
    }
    def ajaxGetPersonajes(){
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }
}
