package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import org.joda.time.LocalDate

class Personaje {
    String nombre
    Integer puntosDeFuerza
    LocalDate fechaCreacion
    String gritoDeGuerra

    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable: false
        puntosDeFuerza nullable: false
        fechaCreacion nullable: true
        arma nullable: false
        gritoDeGuerra nullable: true
    }
}