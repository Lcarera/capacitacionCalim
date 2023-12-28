package capacitacioncalim.selenium

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

import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys


class SeleniumService {
    private WebDriver driver

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
        System.setProperty("webdriver.chrome.driver","chromedriver_linux64/chromedriver");

        // Set the path to the Chrome binary
        options.setBinary("/home/lks/Documents/google-chrome-test/chrome-linux64/chrome");
		
		options.addArguments('--kiosk-printing')
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--disable-extensions")

        driver = new ChromeDriver(options)
        driver.metaClass.remoto = false
		
		return this.driver
	}   

	def getInfoVideo(String titulo) {
        def downloadPath = " "
		def driver = inicializarDriver(downloadPath)

        try {
            def youtubeSearchUrl = "https://www.youtube.com/results?search_query=${titulo.replace(' ', '+')}"
            driver.get(youtubeSearchUrl)

            def firstVideoLink = driver.findElement(By.cssSelector("a#video-title"))
            firstVideoLink.click()

            driver.manage().window().maximize()

            def wait = new WebDriverWait(driver, 10) 
            def likesElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[2]/div[2]/div/div/ytd-menu-renderer/div[1]/segmented-like-dislike-button-view-model/yt-smartimation/div/div/like-button-view-model/toggle-button-view-model/button/div[2]")))
            def likes = likesElement.getText()

            scrollUntilVisible("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[4]/div[1]/div/ytd-text-inline-expander/tp-yt-paper-button[1]")

            def showMoreButton = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[4]/div[1]/div/ytd-text-inline-expander/tp-yt-paper-button[1]"))
            showMoreButton.click()

            def expandedDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-watch-metadata/div/div[4]/div[1]/div/ytd-text-inline-expander/yt-attributed-string")))
            def description = expandedDescription.getText()

            scrollUntilVisible("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-comments/ytd-item-section-renderer/div[1]/ytd-comments-header-renderer/div[1]/h2/yt-formatted-string/span[1]")

            def commentsCountElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[2]/ytd-comments/ytd-item-section-renderer/div[1]/ytd-comments-header-renderer/div[1]/h2/yt-formatted-string/span[1]")))
            def commentsCount = commentsCountElement.getText()

            println("Likes: $likes")
            println("Description: $description")
            println("Comments: $commentsCount")

			def videoInfo = [likes: likes, description: description, commentsCount: commentsCount]

            return videoInfo

        } finally {
            driver.quit()
        }
    }

    // Function to scroll until the specified element is in view
    def scrollUntilVisible(String xpath) {
        JavascriptExecutor js = (JavascriptExecutor) driver
        boolean elementVisible = false
        while (!elementVisible) {
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", driver.findElement(By.xpath(xpath)))
            Thread.sleep(3000) 
            try {
                WebElement element = driver.findElement(By.xpath(xpath))
                elementVisible = element.isDisplayed()
            } catch (NoSuchElementException | StaleElementReferenceException e) {

            }
        }
    }
}