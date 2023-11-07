package capacitacioncalim

import capacitacioncalim.personaje.Arma
import capacitacioncalim.personaje.Personaje
import capacitacioncalim.inicializacion.JsonInicializacion
import org.joda.time.LocalDate
class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
        crearArmas()
        crearPersonajes()
    }
    def destroy = {
    }

    def crearArmas() {
        def armas = [
            ["nombre": "Arco", "puntosAtaque": 12],
            ["nombre": "Espada", "puntosAtaque": 20],
            ["nombre": "Martillo", "puntosAtaque": 24]
        ]
        armas.each { arma ->
            if(Arma.findByNombre(arma.nombre)) return
            new Arma(arma).save(flush: true, failOnError: true)
        }
    }

    def crearPersonajes() {
        def personajes = [
            ["nombre": "Test1", "puntosFuerza": 12, "puntosSalud": 12, "gritoGuerra": "Peleo mucho", 
             "arma": Arma.findByNombre('Martillo'), "fechaCreacion": new LocalDate()],
            ["nombre": "Test2", "puntosFuerza": 20, "puntosSalud": 20, "gritoGuerra": "Peleo mejor", 
             "arma": Arma.findByNombre('Arco'), "fechaCreacion": new LocalDate()],
            ["nombre": "Test3", "puntosFuerza": 24, "puntosSalud": 24, "gritoGuerra": null, 
             "arma": Arma.findByNombre('Espada'), "fechaCreacion": new LocalDate()]
        ]
        personajes.each { personaje ->
            if(Personaje.findByNombre(personaje.nombre)) return
            new Personaje(personaje).save(flush: true, failOnError: true)
        }
    }
}
