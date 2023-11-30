package capacitacioncalim.personaje

import org.joda.time.LocalDate
import capacitacioncalim.User

class Personaje {

    String nombre
    Long puntosFuerza
    Long puntosSalud
    String gritoGuerra

    LocalDate fechaCreacion

    Arma arma

    static belongsTo = [user: User]

    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        gritoGuerra nullable: true
        fechaCreacion nullable: false
        arma nullable: false
        user nullable: true
    }

}