package capacitacioncalim.personaje

import capacitacioncalim.personaje.ArmaService
import capacitacioncalim.User

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import org.joda.time.LocalDate
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PersonajeService {
    def sessionFactory
    ArmaService armaService
    SpringSecurityService springSecurityService

    public List<Personaje> listPersonajes() {

        def query= """  
        SELECT 
            personaje.id as id,
            personaje.nombre as nombre,
            personaje.puntos_salud as puntosSalud,
            personaje.puntos_fuerza as puntosFuerza,
            to_char( personaje.fecha_creacion, 'DD/MM/yyyy') as fechaCreacion,
            personaje.grito_guerra as gritoGuerra,
            personaje.user_id as user,
            arma.nombre as arma
        FROM personaje personaje
        JOIN arma arma
        on arma.id = personaje.arma_id; """
    
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombre
            item["puntosSalud"] = it.puntossalud
            item["puntosFuerza"] = it.puntosfuerza
            item["fechaCreacion"] = it.fechacreacion
            item["gritoGuerra"] = it.gritoguerra ?: '-'
            item["user"] = it.user
            item["arma"] = it.arma
           
            return item
        }    
        return personajes
    } 

        public List<Personaje> listPersonajesFilter(userId) {
            
        def query= """  
        SELECT 
            personaje.id as id,
            personaje.nombre as nombre,
            personaje.puntos_salud as puntosSalud,
            personaje.puntos_fuerza as puntosFuerza,
            to_char( personaje.fecha_creacion, 'DD/MM/yyyy') as fechaCreacion,
            personaje.grito_guerra as gritoGuerra,
            personaje.user_id as user,
            arma.nombre as arma
        FROM personaje personaje
        JOIN arma arma
        on arma.id = personaje.arma_id
        WHERE personaje.user_id = :userId ;"""
    
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setParameter('userId', userId).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombre
            item["puntosSalud"] = it.puntossalud
            item["puntosFuerza"] = it.puntosfuerza
            item["fechaCreacion"] = it.fechacreacion
            item["gritoGuerra"] = it.gritoguerra ?: '-'
            item["user"] = it.user
            item["arma"] = it.arma
           
            return item
        }    
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        assert command.nombre != null: "El nombre no es validofinerror"
        assert command.armaId != null: "Seleccione un armafinerror"
        assert command.puntosFuerza > 0: "Los puntos de fuerza deben ser mayores a 0finerror" 
        assert command.puntosSalud > 0: "Los puntos de salud deben ser mayores a 0finerror" 

        Personaje personaje = new Personaje()

        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        Arma arma = armaService.getArma(command.armaId)
        personaje.arma = arma
        personaje.gritoGuerra = command.gritoGuerra
        User user = getUsuarioActual()
        personaje.user = user
        personaje.fechaCreacion = new LocalDate()
        
        return personaje.save(flush:true, failOnError:true)
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        assert command.nombre != null: "El nombre no es validofinerror"
        assert armaId != null: "Seleccione un armafinerror"
        assert command.puntosFuerza > 0: "Los puntos de fuerza deben ser mayores a 0finerror" 
        assert command.puntosSalud > 0: "Los puntos de salud deben ser mayores a 0finerror" 

        Personaje personaje = Personaje.get(command.id)
        Arma arma = getArma(command.armaId)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        return personaje.save(flush:true, failOnError:true)
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true, failOnError:true)
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
        personajeCommand.user = personaje.user.id
        personajeCommand.armaId = personaje.arma.id
        return personajeCommand
    }

    def getPersonajeMasPoderoso()
    {
        def query= """SELECT 
        personaje.nombre as nombre,
        personaje.puntos_fuerza as puntosFuerza,
        arma.nombre as arma,
        (personaje.puntos_fuerza + arma.puntos_ataque) as poderTotal
        FROM personaje personaje
        join arma arma
        on arma.id = personaje.arma_id
        order by poderTotal desc limit 1;"""

        def personaje = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["nombre"] = it.nombre
            item["puntosFuerza"] = it.puntosfuerza
            item["arma"] = it.arma
            item["poderTotal"] = it.podertotal
            return item
        }

        return personaje
    }

    private def getUsuarioActual() {
        def usuarioActual = springSecurityService.currentUser
            return usuarioActual
    }
}