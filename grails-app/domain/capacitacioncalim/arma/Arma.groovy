package capacitacioncalim.arma

import capacitacioncalim.personaje.Personaje

class Arma{
    String nombre
    Integer puntosAtaque

    static hasMany = [personajes: Personaje]

    static constraints = {
        nombre nullable : false
        puntosAtaque nullable : false
    }
}