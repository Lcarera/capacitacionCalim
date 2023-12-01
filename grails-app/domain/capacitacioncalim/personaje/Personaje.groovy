package capacitacioncalim.personaje

import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma
import capacitacioncalim.User

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra
    Arma arma

    static belongsTo = [user: User]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: true
        gritoGuerra nullable: true
        arma nullable: false
        user nullable: false
    }
}