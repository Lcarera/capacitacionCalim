package capacitacioncalim.personaje
import org.joda.time.LocalDate

import capacitacioncalim.arma.Arma


class Personaje {
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    LocalDate fechaCreacion
    String gritoGuerra
    static belongsTo = [arma: Arma]


    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: false
    }
}