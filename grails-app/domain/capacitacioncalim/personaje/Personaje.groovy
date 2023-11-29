package capacitacioncalim.personaje
import org.joda.time.LocalDate
import capacitacioncalim.User

import capacitacioncalim.arma.Arma


class Personaje {
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    LocalDate fechaCreacion
    String gritoGuerra
    Arma arma 


    static belongsTo = [user: User]

    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: false
        user nullable: false

    }
}