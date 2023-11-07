package capacitacioncalim.arma

import capacitacioncalim.personaje.Personaje


class Arma {
    String nombre
    Integer puntosAtaque

    static hasMany = [personaje: Personaje]

    static constraints = {
        nombre nullable: false
        puntosAtaque nullable: false

    }
}


//Arco 12
//Espada 20
//Martillo 24