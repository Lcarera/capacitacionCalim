package capacitacioncalim

import capacitacioncalim.inicializacion.Inicializacion
import capacitacioncalim.inicializacion.JsonInicializacion

class BootStrap {

    def init = { servletContext ->
        JsonInicializacion.inicializar()
        Inicializacion.comienzo()
    }
    def destroy = {
    }

    
}
