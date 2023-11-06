package capacitacioncalim.personaje
import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap

class PersonajeService{

def sessionFactory

    public List<Personaje> listPersonajes() {
        return Personaje.list()
    } 

    public List<Arma> listArmas() {
        Arma.list()
    }

    public Personaje save(PersonajeCommand command) {
        //assert command.ano > 0: "El ano debe ser mayor a 0finerror"
        Arma arma = Arma.get(command.armaId)
        command.fechaCreacion = new LocalDate()
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        println personaje.fechaCreacion
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public saveArma(Arma armaRecibida){
        Arma arma = new Arma()
        arma.nombre = armaRecibida.nombre
        arma.puntosAtaque = armaRecibida.puntosAtaque
        arma.save(flush:true, failOnError:true)
    }
    
    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        //assert command.ano > 0: "El ano debe ser mayor a 0finerror"
        Arma arma = Arma.get(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError: true)
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
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoGuerra = personaje.gritoGuerra
        personajeCommand.armaId = personaje.armaId

        return personajeCommand
    }

    public getPersonajesSQL(){
        def query = "select p.id, p.nombre as nombre_personaje, p.puntos_salud, p.puntos_fuerza,p.fecha_creacion,p.grito_guerra, a.nombre as nombre_arma from personaje as p join arma as a on p.arma_id = a.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre_personaje
            item.puntosSalud = it.puntos_salud
            item.puntosFuerza = it.puntos_fuerza
            item.fechaCreacion = it.fecha_creacion
            item.gritoGuerra = it.grito_guerra
            item.armaNombre = it.nombre_arma
            return item
        }
        return personajes
    }
}