package capacitacioncalim.editorial

import grails.validation.Validateable

class EditorialCommand implements Validateable{
    
    Long id
    Long version
    
    String nombre
    String direccion
    Integer anoCreacion

    static constraints = {
        id nullable: true
        version nullable: true
        nombre nullable: false
        direccion nullable: false
        anoCreacion nullable: false
    }
} 