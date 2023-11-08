package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
import groovy.transform.CompileStatic
import capacitacioncalim.arma.Arma

@CompileStatic
class BootStrap {


    def init = { servletContext ->

        def registrosCreados = Arma.findAll()

        if (!registrosCreados) {
            def arco = new Arma(nombre: 'Sable', puntosDeAtaque: 20)
            def espada = new Arma(nombre: 'Lanzacohetes',  puntosDeAtaque: 40)
            def martillo = new Arma(nombre: 'Llave',  puntosDeAtaque: 15)
            arco.save(failOnError: true)
            espada.save(failOnError: true)
            martillo.save(failOnError: true)
        }
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }
}