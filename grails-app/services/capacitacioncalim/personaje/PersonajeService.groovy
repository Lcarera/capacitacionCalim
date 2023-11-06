package capacitacioncalim.personaje

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap
import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma

@Transactional
class PersonajeService {
    def sessionFactory

    public List<Personaje> listPersonajes() {
        def q = "SELECT a.id, a.version, a.nombre, a.puntos_salud, a.puntos_fuerza, a.grito_guerra, a.fecha_creacion, b.nombre AS arma FROM personaje a JOIN arma b ON a.arma_id = b.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.version = it.version
            item.nombre = it.nombre
            item.puntosSalud = it.puntos_salud
            item.puntosFuerza = it.puntos_fuerza
            item.gritoGuerra = it.grito_guerra
            item.fechaCreacion = it.fecha_creacion
            item.arma = it.arma
            return item
        }
        return personajes
    }

    public List<Arma> listArmas() {
        return Arma.list()
    }

    public Personaje getPersonajeMasFuerte() {
        def criteria = Personaje.createCriteria()
        def personajeMasFuerte = criteria.get {
            order("puntosFuerza", "desc")
            maxResults(1)
        }
        return personajeMasFuerte
    }

    public Personaje save(PersonajeCommand command) {
        Personaje personaje = new Personaje()
        Arma arma = Arma.get(command.armaId)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.gritoGuerra = command.gritoGuerra
        personaje.fechaCreacion = LocalDate.now()
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        Personaje personaje = Personaje.get(command.id)
        Arma arma = Arma.get(command.armaId)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
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
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personajeCommand.nombre = personaje.nombre
        personajeCommand.puntosSalud = personaje.puntosSalud
        personajeCommand.puntosFuerza = personaje.puntosFuerza
        personajeCommand.gritoGuerra = personaje.gritoGuerra
        personajeCommand.armaId = personaje.armaId
        return personajeCommand
    }


    }
