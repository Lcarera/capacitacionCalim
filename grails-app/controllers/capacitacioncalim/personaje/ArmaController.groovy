package capacitacioncalim.personaje

import grails.converters.JSON
import capacitacioncalim.Auxiliar
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class ArmaController {

    def armaService

    def ajaxGetArmas() {
        def armas = armaService.listArmas()
        render armas as JSON
    }

}