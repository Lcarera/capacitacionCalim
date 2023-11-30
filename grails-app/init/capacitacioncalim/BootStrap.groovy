package capacitacioncalim

import capacitacioncalim.inicializacion.Inicializacion
import capacitacioncalim.inicializacion.JsonInicializacion

class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
<<<<<<< HEAD
        crearArmas()
=======
        Inicializacion.comienzo()
>>>>>>> main
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

    def destroy = {
    }

    
}
