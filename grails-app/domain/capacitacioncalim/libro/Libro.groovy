package capacitacioncalim.libro

class Libro {
    String titulo
    String autor

    static constraints = {
        titulo nullable: false
        autor nullable: false
    }
}