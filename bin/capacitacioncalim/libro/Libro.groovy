package capacitacioncalim.libro

class Libro {
    String titulo
    String autor
    Integer ano

    static constraints = {
        titulo nullable: false
        autor nullable: false
        ano nullable: false
    }
}