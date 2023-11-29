package capacitacioncalim

import capacitacioncalim.inicializacion.Inicializacion
import capacitacioncalim.inicializacion.JsonInicializacion

class BootStrap {


    def init = { servletContext ->

        def registrosCreados = Arma.findAll()

        if (!registrosCreados) {
            def arco = new Arma(nombre: 'Arco', puntosDeAtaque: 12)
            def espada = new Arma(nombre: 'Espada',  puntosDeAtaque: 20)
            def martillo = new Arma(nombre: 'Martillo',  puntosDeAtaque: 24)
            arco.save(failOnError: true)
            espada.save(failOnError: true)
            martillo.save(failOnError: true)
        }
        JsonInicializacion.inicializar()
        Inicializacion.comienzo()
    }
    def destroy = {
    }

    
}
