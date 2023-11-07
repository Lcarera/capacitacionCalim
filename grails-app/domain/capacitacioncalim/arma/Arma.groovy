package capacitacioncalim.arma

import capacitacioncalim.personaje.Personaje

class Arma {
    String nombre
    Integer puntosFuerza

    static hasMany = [personajes: Personaje]
    static constraints = {
        nombre nullable: false
        puntosFuerza nullable: false
    }
}