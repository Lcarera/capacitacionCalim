package capacitacioncalim.selenium


import capacitacioncalim.selenium.SeleniumService
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import java.util.concurrent.TimeUnit

class SeleniumController {

   def seleniumService

    def index() {
        render (view: 'list')
    }

    def obtenerInformacion() {
            def url = params.url
            def opcion = params.opcion

            try {
                def String informacion;

                switch(opcion) {
                case "likes":
                    informacion = seleniumService.getLikes(url)
                    break
                case "descripcion":
                    informacion = seleniumService.getDescripcion(url)
                    break
                case "vistas":
                    informacion = seleniumService.getVistas(url)
                    break
                default:
                    throw new IllegalArgumentException("Opción no válida: $opcion")
                }

                render (view: 'resultado', model: [informacion: informacion])
            } catch (Exception e){
                println(e)       
                render (view: 'list')         
            }
        }
    
}