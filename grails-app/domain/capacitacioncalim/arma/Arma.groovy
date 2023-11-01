package capacitacioncalim.arma

class Arma {
    String nombre
    Integer puntosAtaque
    
    static constraints = {
        nombre nullable: false
        puntosAtaque nullable: false
    }
}