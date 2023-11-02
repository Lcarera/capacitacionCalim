package capacitacioncalim.personaje
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import grails.validation.Validateable

class PersonajeCommand implements Validateable{
    
    Long id
    Long version

    String nombre
    Integer puntosSalud 
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra

    static constraints = {
        id nullable: true
        version nullable: true
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: true
        gritoGuerra nullable: true
    }
} 