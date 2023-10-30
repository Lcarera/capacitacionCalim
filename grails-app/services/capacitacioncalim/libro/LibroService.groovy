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
<<<<<<< HEAD

        Editorial editorial = editorialService.getEditorial(command.editorialId)
        Libro libro = new Libro(titulo: command.titulo, autor: command.autor, ano: command.ano, editorial: editorial)
=======
        Editorial editorial = editorialService.getEditorial(command.editorialId)
        Libro libro = new Libro()
        libro.titulo = command.titulo
        libro.autor = command.autor
        libro.ano = command.ano
        libro.editorial = editorial
>>>>>>> main
        libro.save(flush:true, failOnError:true)
        return libro
    }

    public Libro getLibro(Long id) {
        return Libro.get(id)
    }

<<<<<<< HEAD
    public Libro update(LibroCommand command) {
=======
    public Libro update(command) {
        Editorial editorial = editorialService.getEditorial(command.editorialId)
>>>>>>> main
        Libro libro = Libro.get(command.id)
        libro.titulo = command.titulo
        libro.autor = command.autor
        libro.ano = command.ano
<<<<<<< HEAD
        Editorial editorial = editorialService.getEditorial(command.editorialId)
=======
>>>>>>> main
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

<<<<<<< HEAD
    def getLibroCommand(Long id){
        def libro = getLibro(id)
        println(libro.editorial)
        def command = new LibroCommand()
            command.id = libro.id
            command.titulo = libro.titulo
            command.autor = libro.autor
            command.ano = libro.ano
            command.editorialId = libro.editorial.id

        return command
=======
    def getLibroCommand(Long id) {
        def libro = Libro.get(id)
        def libroCommand = new LibroCommand()
        libroCommand.id = libro.id
        libroCommand.version = libro.version
        libroCommand.titulo = libro.titulo 
        libroCommand.autor = libro.autor
        libroCommand.ano = libro.ano
        libroCommand.editorialId = libro.editorialId

        return libroCommand
>>>>>>> main
    }
}