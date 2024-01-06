package testchromedriver.selenium

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
	private WebDriver driver

	public WebDriver inicializarDriver(String downloadPath) throws AssertionError{
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
        System.setProperty("webdriver.chrome.driver","../chromedriver_linux64/chromedriver.exe");
		
		options.addArguments('--kiosk-printing')
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--disable-extensions")

        this.driver = new ChromeDriver(options)
        this.driver.metaClass.remoto = false
		
		return this.driver
	}

	WebDriver getDriver() {
        return this.driver
    }
}