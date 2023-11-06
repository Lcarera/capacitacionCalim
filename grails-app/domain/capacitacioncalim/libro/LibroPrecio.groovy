package capacitacioncalim.libro

import capacitacioncalim.libreria.Libreria
class LibroPrecio {

    Double precio
    Long stock

    static belongsTo = [libro: Libro, libreria: Libreria]

    static constraints = {
        precio nullable: false
        stock nullable: false
        libro nullable: false
        libreria nullable: false
    }

    
}