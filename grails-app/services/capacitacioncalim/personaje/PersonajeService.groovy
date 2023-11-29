package capacitacioncalim.personaje

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap
import org.joda.time.LocalDate
import java.util.Date
import capacitacioncalim.arma.Arma
import capacitacioncalim.User
@Transactional
class PersonajeService {

    def sessionFactory
    
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
            arma.nombre as arma,
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
            item["arma"] = it.arma
            item["user"] = it.user
           
            return item
        }    
        
        return personajes
    } 
    public List<Personaje> listPersonajesUsuario(User user) {

        def query= """  
        SELECT 
            personaje.id as id,
            personaje.nombre as nombre,
            personaje.puntos_salud as puntosSalud,
            personaje.puntos_fuerza as puntosFuerza,
            to_char( personaje.fecha_creacion, 'DD/MM/yyyy') as fechaCreacion,
            personaje.grito_guerra as gritoGuerra,
            personaje.user_id as user,
            arma.nombre as arma,
        FROM personaje personaje
        JOIN arma arma
        on arma.id = personaje.arma_id
        where personaje.user_id = ${user.id}; """
    
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombre
            item["puntosSalud"] = it.puntossalud
            item["puntosFuerza"] = it.puntosfuerza
            item["fechaCreacion"] = it.fechacreacion
            item["gritoGuerra"] = it.gritoguerra ?: '-'
            item["arma"] = it.arma
            item["user"] = it.user
           
            return item
        }    
        
        return personajes
    } 
    public Personaje save(PersonajeCommand command) {
        assert command.nombre != null: "El Nombre no puede estar vaciofinerror" 
        assert command.puntosFuerza > 0 || command.puntosFuerza != null: "Los puntos de fuerza deben ser un numero y ser mayor a 0finerror" 
        assert command.puntosSalud > 0 || command.puntosSalud != null: "Los puntos de salud deben ser un numero y ser mayor a 0finerror" 
        assert command.armaId != null: "El arma debe ser elegida ahorafinerror" 

        Arma arma = getArma(command.armaId)
        User user = getUser(command.userId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = new LocalDate()
        personaje.gritoGuerra = command.gritoGuerra
            
        personaje.arma = arma
        personaje.user = user
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        //assert command.ano > 0: "EL año debe ser mayor a 0finerror"
        //Editorial editorial = editorialService.getEditorial(command.editorialId)
       assert command.nombre != null || command.nombre != " ": "El Nombre no puede estar vaciofinerror" 
        assert command.puntosFuerza > 0 || command.puntosFuerza != null: "Los puntos de fuerza no pueden ser menor a 0finerror" 
        assert command.puntosSalud > 0 || command.puntosSalud != null: "Los puntos de salud no pueden ser menor a 0finerror" 
        assert command.armaId != null: "El arma debe ser elegida ahorafinerror" 

        Personaje personaje = Personaje.get(command.id)
        Arma arma = getArma(command.armaId)
        User user = getUser(command.userId)

        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = personaje.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        personaje.user = user
        personaje.save(flush:true, failOnError: true)
        return personaje
    }
    
    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true)
        return personaje
    }

    def getPersonajeCommand(Long id) {
        def personaje = getPersonaje(id)
        def personajeCommand = new PersonajeCommand()
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personajeCommand.nombre= personaje.nombre 
        personajeCommand.puntosFuerza = personaje.puntosFuerza
        personajeCommand.puntosSalud = personaje.puntosSalud
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoGuerra = personaje.gritoGuerra

        personajeCommand.armaId = personaje.armaId
        personajeCommand.userId = personaje.userId
        return personajeCommand
    }

    def listArmas(){
        def query ="""select
        ar.id,
        ar.nombre as nombrearma,
        ar.puntos_ataque as puntosataque
        from arma ar;
        """
        def armas = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombrearma
            item["puntosAtaque"] = it.puntosataque
            return item
        }    
        return armas
    }
    
    def getArma(long id){
        return Arma.get(id)
        
    }

    def getMasPoderoso()
    {
        def query= """SELECT 
        pers.nombre as nombrePersonaje,
        pers.puntos_fuerza as puntosFuerza,
        ar.nombre as arma,
        (ar.puntos_ataque + puntos_fuerza) as poderTotal

        FROM personaje pers
        join arma ar on pers.arma_id = ar.id order by poderTotal desc limit 1;"""

        def personaje = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["nombre"] = it.nombrepersonaje
            item["puntosFuerza"] = it.puntosfuerza
            item["arma"] = it.arma
            item["poderTotal"] = it.podertotal
            return item
            }

        return personaje
    }
    /*
    def crearArmas()
    {
        armas = listArmas()
        if (armas.size() == null)
        {
            def queryCrear= """
            INSERT INTO arma(id, version, nombre, puntos_ataque) values (1, 0, 'Arco', 12);
            INSERT INTO arma(id, version, nombre, puntos_ataque) values (1, 0, 'Espada', 20);
            INSERT INTO arma(id, version, nombre, puntos_ataque) values (1, 0, 'Martillo', 24);
            """
        
            sessionFactory.currentSession.beginTransaction()
            sessionFactory.currentSession.createSQLQuery(queryCrear)
            sessionFactory.currentSession.getTransaction().commit()
        }
            
        
    }
    */  

}