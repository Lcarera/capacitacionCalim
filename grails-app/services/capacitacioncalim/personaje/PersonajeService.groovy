package capacitacioncalim.personaje
import grails.transaction.Transactional
import capacitacioncalim.arma.Arma
import capacitacioncalim.User
import org.joda.time.LocalDate
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers
import capacitacioncalim.AccessRulesService
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PersonajeService{
    def sessionFactory

    public List<Arma> listArmas(){
        return Arma.list()
    }

    public List<Personaje> listPersonajes(){
        def query ="""
        SELECT
            personaje.id,
            personaje.nombre,
            personaje.puntos_fuerza,
            personaje.puntos_salud,
            TO_CHAR(personaje.fecha_creacion, 'DD/MM/YYYY') AS fecha_creacion,
            personaje.grito_guerra,
            arma.nombre AS arma,
            personaje.user_id as user
        FROM
            personaje personaje
        JOIN
            arma arma
        ON
            personaje.arma_id = arma.id;"""

        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
           item.nombre = it.nombre
           item.puntosSalud = it.puntos_salud
           item.puntosFuerza = it.puntos_fuerza
           item.fechaCreacion = it.fecha_creacion
           item.gritoGuerra = it.grito_guerra ?: '-'
           item.arma = it.arma
           item.user = it.user
           return item
        }
        return personajes   
    }   

    public List<Personaje> listPersonajesUsuario(long userId) {
            println userId
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
        FROM 
            personaje personaje
        JOIN 
            arma arma
        ON 
            arma.id = personaje.arma_id
        WHERE 
            personaje.user_id = :userId ;"""
    
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

    private def getUsuarioActual() {
        def usuarioActual = springSecurityService.currentUser
            return usuarioActual
    }

    def getPersonajeMasFuerte(){
        def query = """
        SELECT
            p.id AS id,
            p.nombre AS personaje_nombre,
            p.puntos_fuerza AS puntos_personaje,
            a.nombre AS arma_nombre,
            a.puntos_fuerza AS puntos_arma,
            (p.puntos_fuerza + a.puntos_fuerza) AS total_puntos
        FROM
            personaje p
        JOIN
            arma a
        ON
            p.arma_id = a.id
        ORDER BY
            total_puntos DESC
        LIMIT 1;"""

        def personaje = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.personaje_nombre
            item.puntosFuerza = it.total_puntos
            item.arma = it.arma_nombre
            return item
        }
        return personaje
    }
    
    public Personaje save(PersonajeCommand command) {   
        assert command.nombre != null: "Campo de nombre invalidofinerror"
        assert command.puntosFuerza > 0: "Campo de puntos de fuerza invalidofinerror"
        assert command.puntosSalud > 0: "Campo de puntos de salud invalidofinerror"
        assert command.armaId != null: "Campo de arma invalidofinerror"
        Personaje personaje = new Personaje()
        Arma arma = Arma.get(command.armaId)
        User user = getUsuarioActual()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = LocalDate.now()
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        personaje.user = user
        personaje.save(flush: true, failOnError: true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        assert command.nombre != null: "Campo de nombre invalidofinerror"
        assert command.puntosFuerza > 0: "Campo de puntos de fuerza invalidofinerror"
        assert command.puntosSalud > 0: "Campo de puntos de salud invalidofinerror"
        assert command.armaId != null: "Campo de arma invalidofinerror"
        Personaje personaje = Personaje.get(command.id)
        Arma arma = Arma.get(command.armaId)
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
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
        personajeCommand.puntosFuerza = personaje.puntosFuerza
        personajeCommand.puntosSalud = personaje.puntosSalud
        personajeCommand.gritoGuerra = personaje.gritoGuerra
        personajeCommand.armaId = personaje.armaId
        personajeCommand.user = personaje.user.id
        return personajeCommand
    }
}