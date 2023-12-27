package testchromedriver.selenium

import org.openqa.selenium.JavascriptExecutor
import java.util.regex.Matcher
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class SeleniumController {
    SeleniumService seleniumService

    def buscarEnVideo() {
        render (view: 'buscarEnVideo')
    }

    def ajaxGetVideoData(BuscarEnVideoCommand command) {
        WebDriver driver = seleniumService.inicializarDriver("")
        String searchQuery = command.busqueda.replace(" ", "+")
        String url = "https://www.youtube.com/results?search_query=" + searchQuery
        String resultado

        try {
            driver.get(url)

            // Navegamos hasta el primer video que resulta del query.
            WebElement primerVideo = driver.findElement(By.cssSelector("#contents ytd-video-renderer"))
            WebElement thumbnail = primerVideo.findElement(By.cssSelector("#thumbnail"))
            String urlVideo = thumbnail.getAttribute("href")
            driver.get(urlVideo)
            
            // A partir del url del video de YT obtenemos la información dependiendo de la propiedad "buscarPor" del command.
            WebDriverWait wait = new WebDriverWait(driver, 10)

            switch (command.buscarPor) {
                case 'descripcion':
                    // Buscamos el contenedor de la descripcion y lo clickeamos para acceder al texto contenido.
                    WebElement botonExpandirDescripcion = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#expand")))
                    botonExpandirDescripcion.click()

                    WebElement divDescripcionTexto = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#description-inline-expander > yt-attributed-string")))
                    resultado = divDescripcionTexto.getText()
                    break
                case 'likes':
                    WebElement botonLikes = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#top-level-buttons-computed like-button-view-model button")))
                    String ariaLabelBotonLikes = botonLikes.getAttribute("aria-label")

                    def patron = /al igual que otras (\d+(,\d+)*) personas/
                    Matcher matcher = (ariaLabelBotonLikes =~ patron)
                    matcher.find()
                    resultado = matcher.group(1)
                    break
                case 'comentarios':
                    JavascriptExecutor js = (JavascriptExecutor) driver
                    int scroll = 50

                    // Como el contenedor de comentarios solo se renderiza cuando scrolleamos hasta cierta profundidad, debemos programar al driver para que haga eso con ayuda del JavascriptExecutor.
                    while (true) {
                        try {
                            WebElement spanComentarios = driver.findElement(By.cssSelector("#count yt-formatted-string span:nth-child(1)"))

                            resultado = spanComentarios.getText()
                            break
                        } catch (Exception e) {
                            try {
                                js.executeScript("window.scrollTo(0, " + scroll + ");")
                                scroll += 50
                                Thread.sleep(250)
                            } catch (InterruptedException ex) {
                                ex.printStackTrace()
                            }
                        }
                    }

                    break
            }
        } finally {
            driver.quit()
            render text: resultado
        }
    }
}