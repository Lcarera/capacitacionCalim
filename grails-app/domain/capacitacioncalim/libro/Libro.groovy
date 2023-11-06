package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial

class Libro {
    String titulo
    String autor
    Integer ano
    static belongsTo = [editorial: Editorial]
    static hasMany = [libroPrecios: LibroPrecio]
    static constraints = {
        titulo nullable: false
        autor nullable: false
        ano nullable: false
        editorial nullable: true
    }
}