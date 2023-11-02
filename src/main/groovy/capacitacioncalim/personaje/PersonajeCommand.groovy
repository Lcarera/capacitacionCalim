package capacitacioncalim.personaje

import grails.validation.Validateable
import org.joda.time.LocalDate

class PersonajeCommand implements Validateable{
    
    Long id
    Long version
    
    String nombre
    Integer puntosDeFuerza
    LocalDate fechaCreacion
    String gritoDeGuerra
    Long armaId

    static constraints = {
        id nullable: true
        version nullable: true
        nombre nullable: false
        puntosDeFuerza nullable: false
        fechaCreacion nullable: false
        gritoDeGuerra nullable: true
        armaId nullable: true
    }
} 