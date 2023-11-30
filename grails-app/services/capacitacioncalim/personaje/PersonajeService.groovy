package capacitacioncalim.personaje
<<<<<<< HEAD
import org.joda.time.LocalDate
import capacitacioncalim.arma.Arma
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap
import java.time.format.DateTimeFormatter

class PersonajeService{

def sessionFactory

    public List<Personaje> listPersonajes() {
        return Personaje.list()
    } 

    public List<Arma> listArmas() {
        Arma.list()
    }

    public Personaje save(PersonajeCommand command) {
        assert command.nombre != null: "Nombre invalidofinerror"
        assert command.puntosFuerza > 0: "Puntos de fuerza invalidosfinerror"
        assert command.puntosSalud > 0: "Puntos de salud invalidosfinerror"
        assert command.armaId != null: "Arma invalidafinerror"
        Arma arma = Arma.get(command.armaId)
        command.fechaCreacion = new LocalDate()
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public saveArma(Arma armaRecibida){
        Arma arma = new Arma()
        arma.nombre = armaRecibida.nombre
        arma.puntosAtaque = armaRecibida.puntosAtaque
        arma.save(flush:true, failOnError:true)
    }
    
=======

import capacitacioncalim.personaje.Arma

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import org.joda.time.LocalDate

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
            item["arma"] = it.arma
           
            return item
        }    
        
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        assert command.nombre != null: "El nombre no es validofinerror"
        assert armaId != null: "Seleccione un armafinerror"
        assert command.puntosFuerza > 0: "Los puntos de fuerza deben ser mayores a 0finerror" 
        assert command.puntosSalud > 0: "Los puntos de salud deben ser mayores a 0finerror" 

        Personaje personaje = new Personaje()

        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        Arma arma = getArma(command.armaId)
        personaje.arma = arma
        personaje.gritoGuerra = command.gritoGuerra
        personaje.fechaCreacion = new LocalDate()
        
        return personaje.save(flush:true, failOnError:true)
    }

>>>>>>> main
    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

<<<<<<< HEAD
    public Personaje update(command) {
        assert command.nombre != null: "Nombre invalidofinerror"
        assert command.puntosFuerza > 0: "Puntos de fuerza invalidosfinerror"
        assert command.puntosSalud > 0: "Puntos de salud invalidosfinerror"
        assert command.armaId != null: "Arma invalidafinerror"
        Arma arma = Arma.get(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        //personaje.fechaCreacion = command.fechaCreacion // LocalDate.now()  
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError: true)
        return personaje
=======
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
>>>>>>> main
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
<<<<<<< HEAD
        personaje.delete(flush:true)
        return personaje
=======
        personaje.delete(flush:true, failOnError:true)
>>>>>>> main
    }

    def getPersonajeCommand(Long id) {
        def personaje = Personaje.get(id)
        def personajeCommand = new PersonajeCommand()
<<<<<<< HEAD
=======

>>>>>>> main
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personajeCommand.nombre = personaje.nombre 
        personajeCommand.puntosSalud = personaje.puntosSalud
        personajeCommand.puntosFuerza = personaje.puntosFuerza
<<<<<<< HEAD
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoGuerra = personaje.gritoGuerra
        personajeCommand.armaId = personaje.arma.id

        return personajeCommand
    }

    public getPersonajesSQL(){
        def query = "select 
        p.id, 
        p.nombre as nombre_personaje, 
        p.puntos_salud, 
        p.puntos_fuerza,
        p.fecha_creacion,
        p.grito_guerra, 
        a.nombre as nombre_arma 
        from personaje as p join arma as a on p.arma_id = a.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre_personaje
            item.puntosSalud = it.puntos_salud
            item.puntosFuerza = it.puntos_fuerza
            item.fechaCreacion = it.fecha_creacion.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            item.gritoGuerra = it.grito_guerra
            item.armaNombre = it.nombre_arma
            return item
        }
        return personajes
=======
        personajeCommand.gritoGuerra = personaje.gritoGuerra
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
>>>>>>> main
    }
}