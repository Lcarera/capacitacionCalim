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

    def metodo(){
        // def dfquery = "Select id as iD, titulo as ti, autor as au, ano as an, editorial_id as edi from Libro;"
        // def libros = sessionFactory.currentSession.createSQLquery(query).List().each{
        //     def item = [:]
        //     item.nombre = it[0]
        //     item.autor = it[1]
        // }
        // return libros

        // def libros = sessionFactory.currentSession.createSQLQuery(dfquery).setResultTransformer(Transformers.aliasToBean(Libro)).list()
        
        def dfquery = "Select Libro.id, Libro.titulo, Libro.autor, Libro.ano, Libro.editorial_id as edid, Editorial.nombre from Libro JOIN Editorial ON Libro.editorial_id = Editorial.id;"

        def libros = sessionFactory.currentSession.createSQLQuery(dfquery).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.titulo = it.nombre
            item.autor = it.autor
            item.ano = it.ano
            item.editorialId = it.edid
            item.editorialNombre = it.nombre 
            return item
        }
        return libros
    }
    
    public List<Libro> listLibros() {
        return Libro.list()
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

    public Libro update(LibroCommand command) {
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