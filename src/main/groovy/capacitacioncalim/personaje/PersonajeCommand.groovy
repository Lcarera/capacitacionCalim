package capacitacioncalim.personaje
import org.joda.time.LocalDate
import grails.validation.Validateable

class PersonajeCommand implements Validateable {
    Long id
    Long version

    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    String gritoGuerra
    LocalDate fechaCreacion
    Long armaId

    static constraints = {
        id nullable : false
        version nullable : false
        nombre nullable : false
        puntosSalud nullable : false
        puntosFuerza nullable : false
        gritoGuerra nullable : false
        fechaCreacion nullable : false
        armaId nullable : false
    }
}