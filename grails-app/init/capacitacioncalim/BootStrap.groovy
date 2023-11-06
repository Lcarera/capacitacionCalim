package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
import groovy.transform.CompileStatic
import capacitacioncalim.arma.Arma

@CompileStatic
class BootStrap {


    def init = { servletContext ->

        def existingRecords = Arma.findAll()

        if (!existingRecords) {
            createSampleRecords()
        }
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }

    private void createSampleRecords() {
        def arco = new Arma(nombre: 'Arco', puntosDeAtaque: 12)
        def espada = new Arma(nombre: 'Espada',  puntosDeAtaque: 20)
        def martillo = new Arma(nombre: 'Martillo',  puntosDeAtaque: 24)
        arco.save(failOnError: true)
        espada.save(failOnError: true)
        martillo.save(failOnError: true)
    }
}
