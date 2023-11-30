package capacitacioncalim.personaje
<<<<<<< HEAD
import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra
=======

import org.joda.time.LocalDate

class Personaje {

    String nombre
    Long puntosFuerza
    Long puntosSalud
    String gritoGuerra

    LocalDate fechaCreacion

>>>>>>> main
    Arma arma

    static constraints = {
        nombre nullable: false
<<<<<<< HEAD
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable : false
    }
=======
        puntosFuerza nullable: false
        puntosSalud nullable: false
        gritoGuerra nullable: true
        fechaCreacion nullable: false
        arma nullable: false
    }

>>>>>>> main
}