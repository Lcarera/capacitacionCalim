package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
import capacitacioncalim.arma.Arma

class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
        
        def armasPorDefecto = [
            ["Arco", 12],
            ["Espada", 20],
            ["Martillo", 24]
        ]

        armasPorDefecto.each { nombre, puntosAtaque ->
            def armaExistente = Arma.findByNombre(nombre)
            if (!armaExistente) {
                // Si no se encuentra el arma, se crea y se guarda en la base de datos
                def nuevaArma = new Arma(nombre: nombre, puntosAtaque: puntosAtaque)
                nuevaArma.save(flush: true)
            }
        }
    }
    def destroy = {
    }
}
