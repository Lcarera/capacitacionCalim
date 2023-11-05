package capacitacioncalim.personaje

import capacitacioncalim.arma.Arma

class PersonajeService{


    public List<Personaje> listPersonajes() {
        return Personaje.list()
    } 

    public List<Arma> listArmas() {
        Arma.list()
    }

    public Personaje save(PersonajeCommand command) {
        //assert command.ano > 0: "El ano debe ser mayor a 0finerror"
        Arma arma = Arma.get(command.armaId)
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
    
    public Personaje getPersonaje(Long id) {
        return Personaje.get(id)
    }

    public Personaje update(command) {
        //assert command.ano > 0: "El ano debe ser mayor a 0finerror"
        Arma arma = Arma.get(command.armaId)
        Personaje personaje = Personaje.get(command.id)
        personaje.nombre = command.nombre
        personaje.puntosSalud = command.puntosSalud
        personaje.puntosFuerza = command.puntosFuerza
        personaje.fechaCreacion = command.fechaCreacion
        personaje.gritoGuerra = command.gritoGuerra
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
        def libroCommand = new PersonajeCommand()
        personajeCommand.id = libro.id
        personajeCommand.version = libro.version
        personajeCommand.nombre = libro.nombre 
        personajeCommand.puntosSalud = libro.puntosSalud
        personajeCommand.puntosFuerza = libro.puntosFuerza
        personajeCommand.fechaCreacion = libro.fechaCreacion
        personajeCommand.gritoGuerra = libro.gritoGuerra
        personajeCommand.armaId = libro.amraId

        return personajeCommand
    }

    public getPersonajesSQL(){
        def query = "select p.id, p.nombre, p.puntos_salud, p.puntos_fuerza,p.fecha_creacion,p.grito_guerra, a.nombre from personaje as p join arma as a on p.arma_id = a.id;"
        def personajes = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosSalud = it.puntos_salud
            item.puntosFuerza = it.puntos_fuerza
            item.fechaCreacion = it.fecha_creacion
            item.gritoGuerra = it.grito_guerra
            item.armaNombre = it.nombre
            return item
        }
        return personajes
    }
}