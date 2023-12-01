package capacitacioncalim.personaje

import org.joda.time.LocalDate

class Personaje {

    String nombre
    Long puntosFuerza
    Long puntosSalud
    String gritoGuerra

    LocalDate fechaCreacion

    Arma arma

    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        gritoGuerra nullable: true
        fechaCreacion nullable: false
        arma nullable: false
    }

}