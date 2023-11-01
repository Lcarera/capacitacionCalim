package capacitacioncalim.personaje
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap

@Transactional
class PersonajeService {
    def sessionFactory

    def metodo(){
        // def dfquery = "Select id as iD, titulo as ti, autor as au, ano as an, editorial_id as edi from Personaje;"
        // def personajes = sessionFactory.currentSession.createSQLquery(query).List().each{
        //     def item = [:]
        //     item.nombre = it[0]
        //     item.autor = it[1]
        // }
        // return personajes

        // def personajes = sessionFactory.currentSession.createSQLQuery(dfquery).setResultTransformer(Transformers.aliasToBean(Personaje)).list()
        
        def dfquery = "Select Personaje.id, Personaje.titulo, Personaje.autor, Personaje.ano, Personaje.editorial_id as edid, Editorial.nombre from Personaje JOIN Editorial ON Personaje.editorial_id = Editorial.id;"

        def personajes = sessionFactory.currentSession.createSQLQuery(dfquery).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.titulo = it.nombre
            item.autor = it.autor
            item.ano = it.ano
            item.editorialId = it.edid
            item.editorialNombre = it.nombre 
            return item
        }
        return personajes
    }
    
    public List<Personaje> listPersonaje() {
        return Personaje.list()
    } 

    public Personaje save(PersonajeCommand command) {
        // Personaje personaje = personajeService.getPersonaje(command.personajeId)
        Personaje personaje = new Personaje()
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.save(flush:true, failOnError:true)
        return personaje
    }

    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(PersonajeCommand command) {
        // Editorial editorial = editorialService.getEditorial(command.editorialId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
        personaje.save(flush:true)
        return personaje
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)
        personaje.delete(flush:true)
        return personaje
    }

    // def getPersonajesByEditorial(Long editorialId) {
    //     def editorial = editorialService.getEditorial(editorialId)
    //     def personajes = Personaje.findAllByEditorial(editorial)
    //     return personajes
    // }

    def getPersonajeCommand(Long id) {
        def personaje = Personaje.get(id)
        def personajeCommand = new PersonajeCommand()
        personajeCommand.id = personaje.id
        personajeCommand.version = personaje.version
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra

        return personajeCommand
    }
}