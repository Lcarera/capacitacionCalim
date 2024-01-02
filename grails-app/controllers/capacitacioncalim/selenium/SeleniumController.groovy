package capacitacioncalim.selenium

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SeleniumController {
    WebDriver driver
    def seleniumService
    def videoyoutube(String link, String elemento) {
        driver = seleniumService.inicializarDriver("/descargas")
        driver.get(link)
        if (elemento == "likes"){
            Thread.sleep(5000)

            WebElement likesElement = driver.findElement(By.cssSelector("#top-level-buttons-computed like-button-view-model button"))

            String likesCount = likesElement.getText()

            Thread.sleep(1000)

            driver.quit();
            render text: likesCount
        }
    }
}
