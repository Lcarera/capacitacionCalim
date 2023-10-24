package capacitacioncalim

import capacitacioncalim.inicializacion.JsonInicializacion
class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
    }
    def destroy = {
    }
}
