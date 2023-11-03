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
    
    def listJson() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def create() {
    }
    def save(PersonajeCommand command){
        personajeService.save(command)
        redirect(action:"list")
    }
    def edit(Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
    }
    def update(PersonajeCommand command){
        personajeService.update(command)
        redirect(action:"list")
    }
    def delete(Long id){
        personajeService.delete(id)
        redirect(action:"list")
    }
    def ajaxGetArmas(){
        def armas = personajeService.listArmas()
        render armas as JSON
    }
}
