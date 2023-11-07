package capacitacioncalim
import capacitacioncalim.inicializacion.JsonInicializacion
import grails.converters.JSON
import capacitacioncalim.Auxiliar

class BootStrap {
    def dataSource
    def personajeService

    def init = { servletContext ->
        personajeService.inicioCrearArmas()
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }
}
