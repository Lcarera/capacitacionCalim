package capacitacioncalim
import capacitacioncalim.arma.Arma
import capacitacioncalim.inicializacion.JsonInicializacion

class BootStrap {

    def init = { servletContext ->
        if (!Arma.count()){
            def armas = [
                new Arma(nombre: 'Pico', puntosFuerza: 15),
                new Arma(nombre: 'Espada', puntosFuerza: 13),
                new Arma(nombre: 'Lanzacohetes', puntosFuerza: 30)
            ]
            armas.each { it.save(failOnError: true) }
        }
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }
}
