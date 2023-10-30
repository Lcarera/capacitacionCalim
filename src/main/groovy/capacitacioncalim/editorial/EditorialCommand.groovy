package capacitacioncalim.editorial

import capacitacioncalim.libro.Libro

class EditorialCommand implements Validateable {
    Long id
    Long version
    
    String nombre
    String direccion
    Integer anoCreacion

    static constraints = {
        nombre nullable: false
        direccion nullable: false
        anoCreacion nullable: false
    }
}