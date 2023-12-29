package capacitacioncalim.selenium
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import capacitacioncalim.Auxiliar

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SeleniumController {

    def seleniumService

    def index() {
        render (view: 'video')
    }



    def ajaxGetInformacionVideo(String titulo, String tipoInformacion) {
    def video = seleniumService.obtenerInformacionVideo(titulo, tipoInformacion)
    def jsonResult = ["result": video]
    render jsonResult as JSON
}

    def list() {
        
    }

    def create() {  
    }

    def save() {
    }

}

