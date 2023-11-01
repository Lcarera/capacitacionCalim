package capacitacioncalim.personaje

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap


@Transactional
class PersonajeService {

    def sessionFactory
    
    public List<Personaje> listPersonajes() {

        def query= """SELECT 
        pers.id as id
        pers.nombre as nombrePersonaje
        pers.puntosFuerza as puntosFuerza
        pers.puntosSalud as puntosSalud
        pers.fechaCreacion as fecha
        pers.gritoGuerra as grito
        ar.nombre as arma
        (ar.puntosAtaque + puntosFuerza) as poderTotal

        FROM personaje pers
        join arma ar on pers.id_arma = ar.id;"""

        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombrePersonaje
            item["puntosFuerza"] = it.puntosFuerza
            item["puntosSalud"] = it.puntosSalud
            item["fecha"] = it.fecha
            item["gritoGuerra"] = it.grito
            item["arma"] = it.arma
            item["poderTotal"] = it.poderTotal
            return item
            }    
        
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        //assert command.ano > 0: "EL año debe ser mayor a 0finerror" 
        //Arma arma = editorialService.getEditorial(command.editorialId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        //personaje.arma = editorial

        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        //assert command.ano > 0: "EL año debe ser mayor a 0finerror"
        //Editorial editorial = editorialService.getEditorial(command.editorialId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        //personaje.arma = editorial
        personaje.save(flush:true)
        return personaje
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true)
        return personaje
    }

    def getPersonajeCommand(Long id) {
        def personaje = Personaje.get(id)
        def personajeCommand = new PersonajeCommand()
        personajeCommand.id = libro.id
        personajeCommand.version = libro.version
        personajeCommand.nombre= libro.nombre 
        personajeCommand.puntosFuerza = libro.puntosFuerza
        personajeCommand.puntosSalud = libro.puntosSalud
        personajeCommand.fechaCreacion = libro.fechaCreacion
        personajeCommand.gritoGuerra = libro.gritoGuerra

        personajeCommand.armaId = libro.armaId


        return personajeCommand
    }
}