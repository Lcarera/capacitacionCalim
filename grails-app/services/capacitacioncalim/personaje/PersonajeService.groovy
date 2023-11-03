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

        def query= """SELECT 
        pers.id as id,
        pers.nombre as nombrePersonaje,
        pers.puntos_fuerza as puntosFuerza,
        pers.puntos_salud as puntosSalud,
        pers.fecha_creacion as fecha,
        pers.grito_guerra as grito,
        ar.nombre as arma,
        (ar.puntos_ataque + puntos_fuerza) as poderTotal

        FROM personaje pers
        join arma ar on pers.arma_id = ar.id;"""

        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item["id"] = it.id
            item["nombre"] = it.nombrepersonaje
            item["puntosFuerza"] = it.puntosfuerza
            item["puntosSalud"] = it.puntossalud
            item["fecha"] = it.fecha
            item["gritoGuerra"] = it.grito
            item["arma"] = it.arma
            item["poderTotal"] = it.podertotal
            return item
            }    
        println("_______________________________________________________________---$personajes")
        return personajes
    } 

    public Personaje save(PersonajeCommand command) {
        //assert command.ano > 0: "EL año debe ser mayor a 0finerror" 
        Arma arma = getArma(command.armaId)
        command.fechaCreacion = new LocalDate().format("yyyy-MM-dd")
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma

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
        Arma arma = getArma(command.armaId)
        println("Cambiar -----------------------------------------------_$command.nombre")
        println("A Cambiar -----------------------------------------------_$personaje.nombre")
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = personaje.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        println("CAMBIADO ---------------------------------------------------------- $personaje.nombre")
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
        println("----------------------------------------------------------$personajeCommand")

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
        join arma ar on pers.arma_id = ar.id order by poderTotal limit 1;"""

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

}