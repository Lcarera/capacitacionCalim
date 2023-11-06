package capacitacioncalim.personaje
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import capacitacioncalim.arma.Arma

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDateTime fechaCreacion
    String gritoGuerra
    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: true

    }
}