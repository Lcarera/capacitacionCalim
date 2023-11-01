package capacitacioncalim.personaje
import grails.transaction.Transactional

@Transactional
class PersonajeService{
    public List<Personaje> listPersonajes(){
        return Personaje.list()
    }
    public Personaje save(PersonajeCommand command) {        
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosFuerza = command.puntosFuerza
        personaje.puntosSalud = command.ano
        personaje.fechaCreacion = fechaCreacion
        personaje.gritoGuerra = gritoGuerra
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
        personaje.puntosSalud = command.ano
        personaje.fechaCreacion = fechaCreacion
        personaje.gritoGuerra = gritoGuerra
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
        personajeCommand.titulo = personaje.titulo 
        personajeCommand.autor = personaje.autor
        personajeCommand.ano = personaje.ano
        personajeCommand.editorialId = personaje.editorialId

        return personajeCommand
    }
}