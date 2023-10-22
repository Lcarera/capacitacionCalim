package capacitacioncalim.editorial

class Editorial {
    String nombre
    String direccion
    Integer anocreacion
    
    static constraints = {
        nombre nullable: false
        direccion nullable: false
        anocreacion nullable: false
    }
}