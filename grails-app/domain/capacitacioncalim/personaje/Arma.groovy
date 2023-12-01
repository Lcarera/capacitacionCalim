package capacitacioncalim.personaje

class Arma {

    String nombre
    Long puntosAtaque
    
    static hasMany = [personajes: Personaje]
    
    static constraints = {
        nombre nullable: false
        puntosAtaque nullable: false
    }
}