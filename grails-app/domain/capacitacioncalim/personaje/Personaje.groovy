package capacitacioncalim.personaje
import capacitacioncalim.User
import capacitacioncalim.arma.Arma
import org.joda.time.LocalDate

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra

    static hasOne = [arma: Arma]
    static belongsTo = [user: User]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: false
        user nullable: false
    }
}