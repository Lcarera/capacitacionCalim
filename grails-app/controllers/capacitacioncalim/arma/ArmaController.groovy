package capacitacioncalim.arma

import grails.converters.JSON
import capacitacioncalim.Auxiliar

class ArmaController {

    def armaService

    def ajaxGetArmas() {
        def armas = armaService.listArmas()
        render armas as JSON
    }

}