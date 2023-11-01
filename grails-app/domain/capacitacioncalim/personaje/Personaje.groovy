package capacitacioncalim.personaje


class Libro {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    LocalDate fechaCreacion
    String gritoGuerra

    static belongsTo = [editorial: Editorial]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
    }
}