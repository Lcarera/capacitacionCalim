package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial

class Libro {
    String titulo
    String autor

    static belongsTo = [editorial: Editorial]
    static constraints = {
        titulo nullable: false
        autor nullable: false
        editorial nullable: false
    }
}