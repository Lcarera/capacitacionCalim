package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial
import capacitacioncalim.editorial.EditorialService
import grails.transaction.Transactional
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers

@Transactional
class LibroService {

    def editorialService

    def sessionFactory

    public List<Libro> libroSql(){
        def q = "Select a.id, a.titulo, a.autor, a.ano, b.nombre from libro a join editorial b on a.editorial_id = b.id;"
        def libros = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.titulo = it.titulo
            item.autor = it.autor
            item.ano = it.ano
            item.editorial = it.nombre
            return item
        }
        return libros
    }
    
    public List<Libro> listLibros() {
        return Libro.list()
    } 

    public Libro save(LibroCommand command) {
        assert command.ano > 0: "Campo de año invalidofinerror"
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
        assert command.ano > 0: "Campo de año invalidofinerror"
        assert command.ano <= 2023: "Campo de año invalidofinerror"
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
        println libro
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