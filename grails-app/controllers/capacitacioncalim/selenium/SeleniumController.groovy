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

    def prueba() {
        render "Hola mundo"
    }

    def listJson() {
        
    }

    def buscarVideo(String titulo) {
        def video = seleniumService.obtenerInformacionVideo(titulo)
        render video as JSON
    }

    def list() {
        
    }

    def create() {  
    }

    def save() {
    }

}

