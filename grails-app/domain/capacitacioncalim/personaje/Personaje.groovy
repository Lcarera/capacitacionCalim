package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma

class Personaje {
    String nombre
    Integer puntosDeFuerza
    String fechaCreacion
    String gritoDeGuerra

    static belongsTo = [arma: Arma]

    static constraints = {
        nombre nullable: false
        puntosDeFuerza nullable: false
        fechaCreacion nullable: false
        arma nullable: false
        gritoDeGuerra nullable: true
    }
}