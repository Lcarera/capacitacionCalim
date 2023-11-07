package capacitacioncalim
import capacitacioncalim.arma.Arma
import capacitacioncalim.inicializacion.JsonInicializacion

class BootStrap {

    def init = { servletContext ->
        if (!Arma.count()){
            def armas = [
                new Arma(nombre: 'Martillo', puntosFuerza: 24),
                new Arma(nombre: 'Espada', puntosFuerza: 20),
                new Arma(nombre: 'Arco', puntosFuerza: 12)
            ]
            armas.each { it.save(failOnError: true) }
        }
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }
}
