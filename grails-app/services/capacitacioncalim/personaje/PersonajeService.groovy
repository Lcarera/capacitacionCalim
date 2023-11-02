package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap


@Transactional
class PersonajeService {

    def sessionFactory
    def armaService
    
    public List<Personaje> listPersonajes() {
        //return Libro.list()

        // def query= """SELECT 
        // lib.id as id,
        // lib.titulo as t,
        // lib.autor as a,
        // lib.ano as an,
        // edi.nombre as nombre
        // FROM libro lib
        // join editorial edi on edi.id = lib.editorial_id;"""

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
        on arma.id = personaje.arma_id;"""


        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombre
            item["puntosSalud"] = it.puntosSalud
            item["puntosFuerza"] = it.puntosFuerza
            item["fechaCreacion"] = it.fechaCreacion
            item["gritoGuerra"] = it.gritoGuerra
            
            return item
            }    
        
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        assert command.puntosSalud > 0: "Los puntos de salud deben ser mayores a 0finerror" 
        assert command.puntosFuerza > 0: "Los puntos de fuerza deben ser mayores a 0finerror" 

        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        assert command.puntosSalud > 0: "Los puntos de salud deben ser mayores a 0finerror" 
        assert command.puntosFuerza > 0: "Los puntos de fuerza deben ser mayores a 0finerror"
        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.arma = arma
        personaje.save(flush:true)
        return personaje
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true)
        return personaje
    }

    // def getLibrosByEditorial(Long editorialId) {
    //     def editorial = editorialService.getEditorial(editorialId)
    //     def libros = Libro.findAllByEditorial(editorial)
    //     return libros
    // }

    def getPersonajeCommand(Long id) {
        def personaje = Personaje.get(id)
        def personajeCommand = new PersonajeCommand()
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personajeCommand.nombre = personaje.titulo 
        personajeCommand.puntosSalud = personaje.puntosSalud
        personajeCommand.puntosFuerza = personaje.puntosFuerza
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoGuerra = personaje.gritoGuerra
        personajeCommand.armaId = personaje.armaId

        return personajeCommand
    }
}