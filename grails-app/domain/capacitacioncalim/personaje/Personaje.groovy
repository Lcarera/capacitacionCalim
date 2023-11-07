package capacitacioncalim.personaje

import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra

    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: true
        gritoGuerra nullable: true
        arma nullable: false
    }
}