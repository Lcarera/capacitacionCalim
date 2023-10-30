package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial
import capacitacioncalim.editorial.EditorialService
import grails.transaction.Transactional

@Transactional
class LibroService {

    def editorialService
    
    public List<Libro> listLibros() {
        return Libro.list()
    }

    public Libro save(LibroCommand command) {

        Editorial editorial = editorialService.getEditorial(command.editorialId)
        Libro libro = new Libro(titulo: command.titulo, autor: command.autor, ano: command.ano, editorial: editorial)
        libro.save(flush:true, failOnError:true)
        return libro
    }

    public Libro getLibro(Long id) {
        return Libro.get(id)
    }

    public Libro update(LibroCommand command) {
        Libro libro = Libro.get(command.id)
        libro.titulo = command.titulo
        libro.autor = command.autor
        libro.ano = command.ano
        Editorial editorial = editorialService.getEditorial(command.editorialId)
        libro.editorial = editorial
        libro.save(flush:true)
        return libro
    }

    public Libro delete(Long id) {
        Libro libro = Libro.get(id)
        libro.delete(flush:true)
        return libro
    }

    def getLibrosByEditorial(Long editorialId) {
        def editorial = editorialService.getEditorial(editorialId)
        def libros = Libro.findAllByEditorial(editorial)
        return libros
    }

    def getLibroCommand(Long id){
        def libro = getLibro(id)
        def command = new LibroCommand(
            id = libro.id
            titulo = libro.titulo
            autor = libro.autor
            ano = libro.ano
            editorialId = libro.editorialId
        )
        return command
    }
}