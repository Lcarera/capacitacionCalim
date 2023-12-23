package capacitacioncalim.selenium
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import capacitacioncalim.Auxiliar

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SeleniumController {
    def seleniumService
    def index() {
        render (view: 'videos')
    }

    def buscarVideo(String titulo) {
        def video = seleniumService.getInfoVideo(titulo)
        render(view: 'videos', model: [video: video])
    }
}