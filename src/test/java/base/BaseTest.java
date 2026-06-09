package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

public class BaseTest {

    // Using ThreadLocal to keep browser sessions thread-safe during parallel execution
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        ChromeOptions options = new ChromeOptions();

        // Run headless if set in the properties file
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080"); // Set default viewport for headless mode
        }

        driver.set(new ChromeDriver(options));
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            // Clean up thread memory to avoid leaks
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}