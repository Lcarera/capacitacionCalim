package capacitacioncalim.Personaje

class Personaje {
    String nombre
    Integer puntosSalud
    Integer puntosFuerza
    String fechaCreacion
    String gritoGuerra

    // static belongsTo = [editorial: Editorial]

    static constraints = {
        nombre nullable: false
        puntosSalud nullable: false
        puntosFuerza nullable: false
        fechaCreacion nullable: false
        gritoGuerra nullable: true
    }
}