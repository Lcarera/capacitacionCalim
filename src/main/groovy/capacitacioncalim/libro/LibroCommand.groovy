package capacitacioncalim.libro

class LibroCommand {
    
    Long id
    Long version
    
    String titulo
    String autor
    Integer ano
    Long editorialId

    static constraints = {
        id nullable: true
        version nullable: true
        titulo nullable: false
        autor nullable: false
        ano nullable: false
        editorialId nullable: true
    }
} 