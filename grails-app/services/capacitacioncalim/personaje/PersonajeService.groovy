package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService
import capacitacioncalim.User
import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap

@Transactional
class PersonajeService {
    def armaService
    def sessionFactory
    
    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje[] listPersonajes() {
        String query = """
            SELECT * FROM personaje;
        """

        Personaje[] personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).list().collect {
            Arma arma = armaService.getArma(it['arma_id'].longValue())
            User user = User.get(it['user_id'])
            
            Personaje personaje = new Personaje(
                nombre: it['nombre'],
                puntosSalud: it['puntos_salud'],
                puntosFuerza: it['puntos_fuerza'],
                fechaCreacion: it['fecha_creacion'],
                gritoGuerra: it['grito_guerra'],
                arma: arma,
                user: user
            )
            personaje.id = it['id']
            personaje.version = it['version']

            return personaje
        }
        
        return personajes
    }

    public Personaje[] listPersonajesByUser(Long userId) {
        String query = """
            SELECT * FROM personaje WHERE user_id = ${userId};
        """

        Personaje[] personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).list().collect {
            Arma arma = armaService.getArma(it['arma_id'].longValue())
            User user = User.get(it['user_id'])
            
            Personaje personaje = new Personaje(
                nombre: it['nombre'],
                puntosSalud: it['puntos_salud'],
                puntosFuerza: it['puntos_fuerza'],
                fechaCreacion: it['fecha_creacion'],
                gritoGuerra: it['grito_guerra'],
                arma: arma,
                user: user
            )
            personaje.id = it['id']
            personaje.version = it['version']

            return personaje
        }
        
        return personajes
    }

    private void validateCommand(PersonajeCommand command) {
        assert command.puntosSalud >= 1 : "Los puntos de salud no pueden ser menores a 1finerror"
        assert command.puntosFuerza >= 1 : "Los puntos de fuerza no pueden ser menores a 1finerror"
        assert command.armaId : "No se han ingresado un armafinerror"
        assert command.nombre : "No se han ingresado un nombrefinerror"
    }

    public Personaje save(PersonajeCommand command) {
        validateCommand(command)

        Arma arma = armaService.getArma(command.armaId)
        
        Personaje personaje = new Personaje(
            nombre: command.nombre,
            puntosSalud: command.puntosSalud,
            puntosFuerza: command.puntosFuerza,
            fechaCreacion: command.fechaCreacion,
            gritoGuerra: command.gritoGuerra,
            arma: arma
        )

        personaje.save(flush: true, failOnError: true)
        return personaje
    }

    public Personaje update(PersonajeCommand command) {
        validateCommand(command)

        Arma arma = armaService.getArma(command.armaId)
        Personaje personaje = Personaje.get(command.id)

        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.gritoGuerra = command.gritoGuerra
        personaje.arma = arma
        
        personaje.save(flush: true, failOnError: true)
        return personaje
    }

    public Personaje delete(Long id) {
        Personaje personaje = Personaje.get(id)

        personaje.delete(flush: true, failOnError: true)
        return personaje
    }

    def getPersonajeCommand(Long id) {
        Personaje personaje = Personaje.get(id)
        PersonajeCommand command = new PersonajeCommand(
            id: id,
            version: personaje.version,
            nombre: personaje.nombre,
            puntosSalud: personaje.puntosSalud,
            puntosFuerza: personaje.puntosFuerza,
            gritoGuerra: personaje.gritoGuerra,
            armaId: personaje.armaId
        )

        return command
    }
}
