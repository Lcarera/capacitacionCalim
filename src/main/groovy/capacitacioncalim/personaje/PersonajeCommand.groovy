package capacitacioncalim.personaje
<<<<<<< HEAD
import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma
=======

>>>>>>> main
import grails.validation.Validateable

class PersonajeCommand implements Validateable{
    
    Long id
    Long version
<<<<<<< HEAD

    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra
=======
    
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    String gritoGuerra

>>>>>>> main
    Long armaId

    static constraints = {
        id nullable: true
        version nullable: true
<<<<<<< HEAD
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        armaId nullable : true
=======
        
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        gritoGuerra nullable: true

        armaId nullable: true

>>>>>>> main
    }
} 