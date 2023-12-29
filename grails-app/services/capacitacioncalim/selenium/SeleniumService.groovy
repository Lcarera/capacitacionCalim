package capacitacioncalim.selenium
import java.util.regex.Matcher
import org.openqa.selenium.*
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.UselessFileDetector
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class SeleniumService {
    private WebDriver inicializarDriver(String downloadPath) throws AssertionError{
		Map<String, Object> prefsMap = new HashMap<String, Object>()
		prefsMap.put("profile.default_content_settings.popups", 0)
		prefsMap.put("download.prompt_for_download", "false")
		prefsMap.put("download.directory_upgrade", "true")
		prefsMap.put("safebrowsing.enabled", "true")
		prefsMap.put("plugins.always_open_pdf_externally", true)
		prefsMap.put("plugins.plugins_disabled", "Chrome PDF Viewer")
		
        File carpeta = new File(downloadPath)
        if(!carpeta.exists())
            carpeta.mkdirs()
    
		prefsMap.put("download.default_directory", downloadPath)
		prefsMap.put("savefile.default_directory", downloadPath)

		ChromeOptions options = new ChromeOptions()
		options.addArguments("--window-size=1400,900")

		options.setExperimentalOption("prefs", prefsMap)
		options.addArguments("--test-type")
        System.setProperty("webdriver.chrome.driver","../chromedriver_linux64/chromedriver");
		
		options.addArguments('--kiosk-printing')
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--disable-extensions")

		
        def driver = new ChromeDriver(options)
        driver.metaClass.remoto = false
		
		return driver
	}    

	def obtenerInformacionVideo(String titulo, String tipoInformacion ) {

        //def downloadPath = "./chromedriver/chromedriver-linux64"
        def downloadPath = " "
		def driver = inicializarDriver(downloadPath)

        try {
            def youtubeSearchUrl = "https://www.youtube.com/results?search_query=${titulo.replace(' ', '+')}"
            driver.get(youtubeSearchUrl)

            def firstVideoLink = driver.findElement(By.cssSelector("a#video-title"))
            firstVideoLink.click()

            Thread.sleep(5000)
            String informacion = null

            switch (tipoInformacion) {
            case "Likes":
                WebDriverWait wait = new WebDriverWait(driver, 10)
                WebElement botonLikes = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#top-level-buttons-computed like-button-view-model button")))
                String ariaLabelBotonLikes = botonLikes.getAttribute("aria-label")                             
                def matcher = (ariaLabelBotonLikes =~ /[\d,]+/)
                informacion = matcher.find() ? matcher.group() : null 
                break
            case "Descripcion":
                    WebDriverWait wait = new WebDriverWait(driver, 10)
                    WebElement botonExpandirDescripcion = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#expand")))
                    botonExpandirDescripcion.click()

                    WebElement divDescripcionTexto = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#description-inline-expander > yt-attributed-string")))
                    informacion = divDescripcionTexto.getText()
                    break
            case "Comentarios":
                JavascriptExecutor js = (JavascriptExecutor) driver
                int scroll = 50

                while (true) {
                    try {
                        WebElement spanComentarios = driver.findElement(By.cssSelector("#count yt-formatted-string span:nth-child(1)"))
                        informacion = spanComentarios.getText()
                        break
                    } catch (NoSuchElementException e) {
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
            default:
                break
        }


            

println("Resultado: $informacion")
return tipoInformacion + ": " + informacion
             

        } finally {
            driver.quit()
        }
    }

}