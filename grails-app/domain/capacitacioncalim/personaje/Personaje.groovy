package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma


class Personaje {
    String nombre
    Integer puntosFuerza
    Integer puntosSalud
    Date fechaCreacion
    String gritoGuerra
    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
        arma nullable: false
    }
}