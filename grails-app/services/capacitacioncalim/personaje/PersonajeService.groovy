package capacitacioncalim.personaje
import grails.transaction.Transactional
import capacitacioncalim.arma.Arma
import org.joda.time.LocalDate

@Transactional
class PersonajeService{
    public List<Arma> listArmas(){
        return Arma.list()
    }
    public List<Personaje> listPersonajes(){
        return Personaje.list()
    }
    public Personaje save(PersonajeCommand command) {        
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.fechaCreacion = LocalDate.now()
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = command.armaId
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.puntosSalud
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = command.armaId
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

        return personajeCommand
    }
}