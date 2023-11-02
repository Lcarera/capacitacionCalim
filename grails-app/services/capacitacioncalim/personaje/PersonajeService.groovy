package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService 
import grails.transaction.Transactional
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers
import org.joda.time.DateTime

import java.text.SimpleDateFormat
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

@Transactional
class PersonajeService {

    def armaService

    def sessionFactory

    public List<Personaje> listPersonajes(){
        def q = "Select a.id, a.nombre, a.puntos_de_fuerza, a.fecha_creacion, a.grito_de_guerra, b.nombre as arma from personaje a join arma b on a.arma_id = b.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosDeFuerza = it.puntos_de_fuerza
            item.fechaCreacion = it.fecha_creacion
            item.gritoDeGuerra = it.grito_de_guerra
            item.arma = it.arma
            return item
        }
        return personajes
    } 

    LocalDate generarFechaActual() {
        DateTimeFormatter formato = DateTimeFormat.forPattern("yyyy-MM-dd")
        String fechaFormateada = formato.print(new LocalDate())
        return formato.parseLocalDate(fechaFormateada)
    }

    public Personaje save(PersonajeCommand command) {
        assert command.puntosDeFuerza > 0: "Campo de puntos de fuerza invalidofinerror"
        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = new Personaje()
        command.fechaCreacion = generarFechaActual()
        personaje.nombre = command.nombre
        personaje.puntosDeFuerza = command.puntosDeFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoDeGuerra = command.gritoDeGuerra
        personaje.arma = arma
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        assert command.ano > 0: "Campo de año invalidofinerror"
        assert command.ano <= 2023: "Campo de año invalidofinerror"
        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosDeFuerza = command.puntosDeFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoDeGuerra = command.gritoDeGuerra
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
        personajeCommand.puntosDeFuerza = personaje.puntosDeFuerza
        personajeCommand.fechaCreacion = personaje.fechaCreacion
        personajeCommand.gritoDeGuerra = personaje.gritoDeGuerra
        personajeCommand.armaId = personaje.armaId

        return personajeCommand
    }
} 