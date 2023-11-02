package capacitacioncalim.arma

import capacitacioncalim.personaje.Personaje

class Arma {
    String nombre
    Integer puntosDeAtaque

    static hasMany = [personaje: Personaje]

    static constraints = {
        nombre nullable: false
        puntosDeAtaque nullable: false
    }
}