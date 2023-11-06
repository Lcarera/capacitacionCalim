package capacitacioncalim.libreria

import capacitacioncalim.libro.LibroPrecio

class Libreria {

    String nombre
    String direccion
    String telefono
    String nombreDueno
    String nombreGerente
    Long horaApertura
    Long horaCierre

    static hasMany = [libros: LibroPrecio]

    static constraints = {
        nombre nullable: false
        direccion nullable: false
        telefono nullable: false
        nombreDueno nullable: false
        nombreGerente nullable: false
        horaApertura nullable: false
        horaCierre nullable: false
    }
}