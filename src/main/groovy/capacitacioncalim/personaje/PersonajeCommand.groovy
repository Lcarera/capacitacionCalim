package capacitacioncalim.personaje

import grails.validation.Validateable

class PersonajeCommand implements Validateable{
    
    Long id
    Long version
    
    String nombre
    Integer puntosDeFuerza
    Integer puntosDeSalud
    String fechaCreacion
    String gritoDeGuerra
    Long armaId

    static constraints = {
        id nullable: true
        version nullable: true
        nombre nullable: false
        puntosDeFuerza nullable: false
        puntosDeSalud nullable: false
        fechaCreacion nullable: false
        gritoDeGuerra nullable: true
        armaId nullable: true
    }
} 