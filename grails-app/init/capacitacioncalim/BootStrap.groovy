package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
        crearArmas()
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
