package capacitacioncalim.libro

import org.hibernate.transform.Transformers
import java.util.LinkedHashMap
import capacitacioncalim.editorial.Editorial
import capacitacioncalim.editorial.EditorialService
import grails.transaction.Transactional

@Transactional
class LibroService {

    def editorialService
    def sessionFactory
    
    public List<Libro> listLibros() {
        return Libro.list()
    } 

    public Libro save(LibroCommand command) {
        assert command.ano > 0: "El ano debe ser mayor a 0finerror"
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
        assert command.ano > 0: "El ano debe ser mayor a 0finerror"
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

    public getLibrosSQL(){
        def query = "select l.id, l.autor, l.titulo, l.ano, e.nombre from libro as l join editorial as e on l.editorial_id = e.id;"
        def libros = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.autor = it.autor
            item.titulo = it.titulo
            item.ano = it.ano
            item.editorialnombre = it.nombre
            return item
        }
        return libros
    }
}