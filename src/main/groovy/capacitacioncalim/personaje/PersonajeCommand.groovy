package capacitacioncalim.personaje

import grails.validation.Validateable

class PersonajeCommand implements Validateable{
    
    Long id
    Long version
    
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    String fechaCreacion
    String gritoGuerra
    Long armaId

    static constraints = {
        id nullable: true

        version nullable: true

        nombre nullable: false

        puntosFuerza nullable: false

        puntosSalud nullable: false

        fechaCreacion nullable: true

        gritoGuerra nullable: true

        armaId nullable: true

    }
} 