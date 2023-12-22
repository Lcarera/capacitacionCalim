package capacitacioncalim.selenium

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import capacitacioncalim.Auxiliar

@Secured(['permitAll'])
class SeleniumController{

    def seleniumService

    def list() {}

    def create() {}

    def search(String titulo){
        driver = seleniumService.inicializarDriver()
        titulo.replace(" ", "+")
        driver.get("https://www.youtube.com/results?search_query=https://www.youtube.com/watch?v=jNQXAC9IVRw")
        likesElement = driver.findElement(By.xpath("//span[contains(text(), 'likes')]/preceding-sibling::button/span"))
        likesCount = likesElement.getText()
        println(likesCount)
    }

}
