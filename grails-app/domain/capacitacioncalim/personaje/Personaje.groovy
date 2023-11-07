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

//Cambiar el BelongsTo a Simplemente Arma puede funcionar?
//pero aun asi el personaje esta fuertemente relacionado al arma, al punto a que es esencial?
//Llegue a la conclusión con una frase Cliche "El arma es la unica cosa que nunca traicionara a su usuario". EL arma es algo fuertemente relacionado a un personaje de aventuras.

    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosSalud nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: false
    }
}