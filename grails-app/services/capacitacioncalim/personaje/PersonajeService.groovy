package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap


@Transactional
class PersonajeService {

    def sessionFactory
    def editorialService
    
    public List<Personaje> listLibros() {
        //return Libro.list()

        def query= """SELECT 
        lib.id as id,
        lib.titulo as t,
        lib.autor as a,
        lib.ano as an,
        edi.nombre as nombre
        FROM libro lib
        join editorial edi on edi.id = lib.editorial_id;"""

        def query= """ SELECT 
        personaje.id as id,
        personaje.nombre as nombre,
        personaje.puntosSalud as puntosSalud,
        personaje.puntosFuerza as puntosFuerza,
        personaje.fechaCreacion as fechaCreacion,
        personaje.gritoGuerra as gritoGuerra,
        arma.nombre
        FROM personaje personaje
        join arma arma
        on 


        def libros = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["titulo"] = it.t
            item["autor"] = it.a
            item["ano"] = it.an
            item["editorial"] = it.nombre
            
            return item
            }    
        
        return libros
    } 

    public Libro save(LibroCommand command) {
        assert command.ano > 0: "EL año debe ser mayor a 0finerror" 
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
        assert command.ano > 0: "EL año debe ser mayor a 0finerror"
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