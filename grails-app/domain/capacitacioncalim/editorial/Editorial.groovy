package capacitacioncalim.editorial

import capacitacioncalim.libro.Libro

class Editorial {

    String nombre
    String direccion
    Integer anoCreacion

    static constraints = {
        nombre nullable: false
        direccion nullable: false
        anoCreacion nullable: false
    }
}