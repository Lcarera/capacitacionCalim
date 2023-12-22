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

    def seleniumService
    def videoyoutube(String link, String elemento) {
        WebDriver driver = seleniumService.inicializarDriver("")
        driver.get(link)
        if (elemento == "likes"){
            WebElement likes = driver.findElement(By.className("yt-spec-button-shape-next__button-text-content"))
            Thread.sleep(5000)
            likes.sendKeys("ChromeDriver");
            likes.submit();
            Thread.sleep(5000)
            driver.quit();
        }
    }
}
