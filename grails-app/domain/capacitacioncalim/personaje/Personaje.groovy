package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import org.joda.time.LocalDate
class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    String gritoGuerra
    LocalDate fechaCreacion

    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable : false
        puntosSalud nullable : false
        puntosFuerza nullable : false
        gritoGuerra nullable : true
        fechaCreacion nullable : false
        arma nullable : false
    }
}