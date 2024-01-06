import grails.plugin.springsecurity.annotation.Secured
import org.openqa.selenium.WebDriver
import org.springframework.web.servlet.ModelAndView




class SeleniumController{
    
    def seleniumService
    
    /*def index() {
        render(view: 'buscarVideo')
    }*/

    def buscarVideo() {}

    def buscarUrl(String tituloVideo){
        WebDriver driver = seleniumService.inicializarDriver("");
        try{
            driver.get("https://www.youtube.com/")

            /*WebElement campoBusqueda = driver.findElement(By.name("search_query"))
            campoBusqueda.sendKeys(tituloVideo)
            campoBusqueda.submit()

            WebElement primerResultado = driver.findElement(By.id("video-title"))
            primerResultado.click()

            Thread.sleep(5000)*/
        } catch (Exception e) {
            e.printStackTrace()
            return new ModelAndView("error").addObject("mensajeError", "Ocurrió un error al buscar el video.")
    }

}

}