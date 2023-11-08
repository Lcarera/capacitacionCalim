package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
import capacitacioncalim.arma.Arma

class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
        def totalRegistros = Arma.count()
        if (totalRegistros == 0) {
            // Si no hay registros en la tabla "Arma", realiza las inserciones
            def armas = [
                [nombre: "Arco", puntosAtaque: 12],
                [nombre: "Espada", puntosAtaque: 20],
                [nombre: "Martillo", puntosAtaque: 24]
            ]
            armas.each { armaData ->
            def nuevaArma = new Arma(nombre: armaData.nombre, puntosAtaque: armaData.puntosAtaque)
            nuevaArma.save(flush: true)
            }
    }
}
    def destroy = {
    }
}


