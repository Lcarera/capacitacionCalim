package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService 
import grails.transaction.Transactional
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers
import org.joda.time.LocalDate

@Transactional
class PersonajeService {

    def armaService

    def sessionFactory

    public List<Personaje> listPersonajes(){
        def q = "Select a.id, a.nombre, a.puntos_de_fuerza, a.puntos_de_salud, a.fecha_creacion, a.grito_de_guerra, b.nombre as arma from personaje a join arma b on a.arma_id = b.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosDeFuerza = it.puntos_de_fuerza
            item.puntosDeSalud = it.puntos_de_salud
            item.fechaCreacion = it.fecha_creacion
            item.gritoDeGuerra = it.grito_de_guerra
            item.arma = it.arma
            return item
        }
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        assert command.puntosDeFuerza > 0: "Campo de puntos de fuerza invalidofinerror"
        assert command.puntosDeSalud > 0: "Campo de puntos de salud invalidofinerror"
        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosDeFuerza = command.puntosDeFuerza
        personaje.puntosDeSalud = command.puntosDeSalud
        personaje.fechaCreacion = (LocalDate.now()).toString('yyyy-MM-dd')
        personaje.gritoDeGuerra = command.gritoDeGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        assert command.puntosDeFuerza > 0: "Campo de puntos de fuerza invalidofinerror"
        assert command.puntosDeSalud > 0: "Campo de puntos de salud invalidofinerror"
        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosDeFuerza = command.puntosDeFuerza
        personaje.puntosDeSalud = command.puntosDeSalud
        personaje.gritoDeGuerra = command.gritoDeGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true, failOnError:true)
        return personaje
    }

    def getPersonajeCommand(Long id) {
        def personaje = Personaje.get(id)
        def personajeCommand = new PersonajeCommand()
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personajeCommand.nombre = personaje.nombre 
        personajeCommand.puntosDeFuerza = personaje.puntosDeFuerza
        personajeCommand.puntosDeSalud = personaje.puntosDeSalud
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoDeGuerra = personaje.gritoDeGuerra
        personajeCommand.armaId = personaje.armaId

        return personajeCommand
    }

    def getPersonajeMasFuerte(){
        def q = "select a.nombre, (a.puntos_de_fuerza + b.puntos_de_ataque) as daño from personaje a, arma b where a.arma_id = b.id group by a.nombre, a.puntos_de_fuerza, b.puntos_de_ataque order by daño desc limit 1;"
        def personaje = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.nombre = it.nombre
            item.daño = it.daño
            return item
        }
        return personaje
    }
} 