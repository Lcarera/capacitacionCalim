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
import java.util.concurrent.TimeUnit


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
        System.setProperty("webdriver.chrome.driver","../chromedriver-linux64/chromedriver");
		
		options.addArguments('--kiosk-printing')
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--disable-extensions")

		
        WebDriver driver = new ChromeDriver(options);
        driver.metaClass.remoto = false
		
		return driver
	}    

	public static getLikes (String link) {
        WebDriver driver = new ChromeDriver();
		try{
			driver.get(link);
 			WebDriverWait wait = new WebDriverWait(driver, 20);
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='top-level-buttons-computed']/segmented-like-dislike-button-view-model/yt-smartimation/div/div/like-button-view-model/toggle-button-view-model/button/div[2]")));
           	WebElement likesElement = driver.findElement(By.xpath("//*[@id='top-level-buttons-computed']/segmented-like-dislike-button-view-model/yt-smartimation/div/div/like-button-view-model/toggle-button-view-model/button/div[2]"));
        	String informacion = likesElement.getText();
		return informacion
		} finally {
			driver.quit();
		}
    }

	public static getVistas (String link) {
        WebDriver driver = new ChromeDriver();
		try{
			driver.get(link);
 			WebDriverWait wait = new WebDriverWait(driver, 20);
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='info']/span[1]")));
           	WebElement vistasElement = driver.findElement(By.xpath("//*[@id='info']/span[1]"));
        	String informacion = vistasElement.getText();
		return informacion
		} finally {
			driver.quit();
		}
    }

	public static getDescripcion (String link) {
        WebDriver driver = new ChromeDriver();
		try{
			driver.get(link);
 			WebDriverWait wait = new WebDriverWait(driver, 20);
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='snippet']")));
           	WebElement descripcionElement = driver.findElement(By.xpath("//*[@id='snippet']"));

			descripcionElement.click();

        	String informacion = descripcionElement.getText();
		return informacion
		} finally {
			driver.quit();
		}
    }	
}