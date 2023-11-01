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
        renderlibro
libro
libro
libro
libro
libro personajes as JSON
    }

    def create() {
    }
    def save(PersonajeCommmand command){

    }
}
