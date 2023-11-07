package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma
import capacitacioncalim.arma.ArmaService
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

    def getPersonajeMasFuerte() {
        String query = """
            SELECT p.id, p.nombre, p.puntos_salud, p.puntos_fuerza, p.fecha_creacion, p.grito_guerra, p.arma_id FROM personaje p JOIN arma a ON p.arma_id = a.id order by p.puntos_fuerza + a.puntos_ataque desc limit 1;
        """

        def resultado = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).uniqueResult()

        if (resultado) {
            Arma arma = Arma.get(resultado['arma_id'])
            def item = [:]

            item.id = resultado.id
            item.nombre = resultado.nombre
            item.puntosSalud = resultado['puntos_salud']
            item.puntosFuerza = resultado['puntos_fuerza']
            item.puntosAtaqueTotal = arma.puntosAtaque + item.puntosFuerza
            item.fechaCreacion = resultado['fecha_creacion'].toString()
            item.gritoGuerra = resultado['grito_guerra']
            item.arma = arma

            return item
        } else {
            return null
        }
    }

    def listPersonajes() {
        String query = """
            SELECT id, nombre, puntos_salud, puntos_fuerza, fecha_creacion, grito_guerra, arma_id FROM personaje;
        """

        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).list().collect {
            Arma arma = Arma.get(it['arma_id'])
            def item = [:]

            item.id = it.id
            item.nombre = it.nombre
            item.puntosSalud = it['puntos_salud']
            item.puntosFuerza = it['puntos_fuerza']
            item.puntosAtaqueTotal = arma.puntosAtaque + item.puntosFuerza
            item.fechaCreacion = it['fecha_creacion'].toString()
            item.gritoGuerra = it['grito_guerra']
            item.arma = arma

            return item
        }

        return personajes
    }

    private void validateCommand(PersonajeCommand command) {
        assert command.puntosSalud >= 1 : "Los puntos de salud no pueden ser menores a 1finerror"
        assert command.puntosFuerza >= 1 : "Los puntos de fuerza no pueden ser menores a 1finerror"
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
        personaje.fechaCreacion = command.fechaCreacion
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
