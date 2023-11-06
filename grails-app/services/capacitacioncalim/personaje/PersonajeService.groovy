package capacitacioncalim.personaje
import capacitacioncalim.arma.Arma
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.util.Date

@Transactional
class PersonajeService {
    def sessionFactory

    def dataBaseQueryPersonajes(){
        // def dfquery = "Select id as iD, titulo as ti, autor as au, ano as an, editorial_id as edi from Personaje;"
        // def personajes = sessionFactory.currentSession.createSQLquery(query).List().each{
        //     def item = [:]
        //     item.nombre = it[0]
        //     item.autor = it[1]
        // }
        // return personajes

        // def personajes = sessionFactory.currentSession.createSQLQuery(dfquery).setResultTransformer(Transformers.aliasToBean(Personaje)).list()
        
        def dbquery = """
        Select Personaje.id,
        Personaje.nombre,
        Personaje.puntos_salud,
        Personaje.puntos_fuerza,
        Personaje.fecha_creacion,
        Personaje.grito_guerra,
        Arma.id as armaid,
        Arma.nombre as armanombre 
        from Personaje 
        JOIN Arma ON Personaje.arma_id = Arma.id;
        """

        def personajes = sessionFactory.currentSession.createSQLQuery(dbquery).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosSalud = it.puntos_salud
            item.puntosFuerza = it.puntos_fuerza
            item.fechaCreacion = it.fecha_creacion
            item.gritoGuerra = it.grito_guerra
            item.armaId = it.armaid
            item.armaNombre = it.armanombre

            println("Items: " + item) 
            return item
        }
        return personajes
    }

    def dataBaseQueryArmas(){
        def dbquery = """
        Select Arma.id,
        Arma.nombre as nombrearma,
        Arma.puntos_ataque puntosataque
        from Arma;
        """

        def armas = sessionFactory.currentSession.createSQLQuery(dbquery).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombrearma
            item.puntosAtaque = it.puntosataque
            return item
        }
        return armas
    }
    
    public List<Personaje> listPersonaje() {
        return Personaje.list()
    } 

    public Arma getArma(Long id) {
        return Arma.get(id)
    }

    public Personaje save(PersonajeCommand command) {
        Arma arma = getArma(command.armaId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        //personaje.fechaCreacion = new LocalDate().format("yyyy-MM-dd")
        personaje.fechaCreacion = new LocalDate()
        personaje.gritoGuerra = command.gritoGuerra ?: ""
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        Arma arma = getArma(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.gritoGuerra = command.gritoGuerra ?: ""
        personaje.arma = arma
        personaje.save(flush:true)
        println("Personaje: " + personaje)
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
}