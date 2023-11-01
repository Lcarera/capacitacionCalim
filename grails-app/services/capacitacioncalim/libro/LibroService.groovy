package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial
import capacitacioncalim.editorial.EditorialService
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap

@Transactional
class LibroService {

    def editorialService
    def sessionFactory

    public def listLibrosDB(){
        String query = """
            SELECT id, titulo, autor, ano, editorial_id AS eid FROM Libro;
        """

        def libros = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).list().collect {
            def item = [:]
            item.id = it.id
            item.titulo = it.titulo
            item.autor = it.autor
            item.ano = it.ano
            item.editorial = Editorial.get(it.eid)
            return item
        }

        return libros
    }
    
    public List<Libro> listLibros() {
        println("No deberia ser llamada")
    } 

    public Libro save(LibroCommand command) {
        Editorial editorial = editorialService.getEditorial(command.editorialId)
        Libro libro = new Libro()
        libro.titulo = command.titulo
        libro.autor = command.autor
        libro.ano = command.ano
        libro.editorial = editorial
        libro.save(flush:true, failOnError:true)
        return libro
    }

    public Libro getLibro(Long id) {
        return Libro.get(id)
    }

    public Libro update(command) {
        Editorial editorial = editorialService.getEditorial(command.editorialId)
        Libro libro = Libro.get(command.id)
        libro.titulo = command.titulo
        libro.autor = command.autor
        libro.ano = command.ano
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
    }
}
