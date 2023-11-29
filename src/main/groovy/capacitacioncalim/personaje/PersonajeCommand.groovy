package capacitacioncalim.personaje
import org.joda.time.LocalDate

import grails.validation.Validateable

class PersonajeCommand implements Validateable{
    
    Long id
    Long version
    
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    LocalDate fechaCreacion
    String gritoGuerra
    Long armaId

    Long userId

    static constraints = {
        id nullable: true

        version nullable: true

        nombre nullable: false

        puntosFuerza nullable: false

        puntosSalud nullable: false

        fechaCreacion nullable: true

        gritoGuerra nullable: true

        armaId nullable: true
        
        userId nullable: true

    }
} 