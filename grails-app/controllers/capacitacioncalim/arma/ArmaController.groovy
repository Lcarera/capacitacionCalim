package capacitacioncalim.arma

import capacitacioncalim.Auxiliar
import grails.converters.JSON

class ArmaController {
    def armaService

    def ajaxGetArmas() {
        def armas = armaService.listArmas()

        render armas as JSON
    }
}