package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverFactory() {
    }

    private static WebDriver createDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        int waitTime = Integer.parseInt(ConfigReader.get("implicit.wait"));

        WebDriver driver = switch (browser) {
            case "chrome" -> initChrome(headless);
            case "firefox" -> initFirefox(headless);
            case "edge" -> initEdge(headless);
            case "safari" -> new SafariDriver();
            default -> throw new RuntimeException("Navegador não suportado: " + browser);
        };

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initChrome(boolean headless) {
        //WebDriverManager.chromedriver().clearResolutionCache().browserVersion("latest").setup();
        WebDriverManager.chromedriver().driverVersion("136.0.7103.94").setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    private static WebDriver initFirefox(boolean headless) {
        WebDriverManager.firefoxdriver()
                .clearResolutionCache()
                .browserVersion("latest")
                .setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        return driver;
    }

    private static WebDriver initEdge(boolean headless) {
        WebDriverManager.edgedriver()
                .clearResolutionCache()
                .browserVersion("latest")
                .setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--start-maximized");
        WebDriver driver = new EdgeDriver(options);
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null || isSessionInvalid(driver)) {
            driver = createDriver();
            driverThread.set(driver);
        }
        return driver;
    }

    private static boolean isSessionInvalid(WebDriver driver) {
        try {
            return ((RemoteWebDriver) driver).getSessionId() == null;
        } catch (Exception e) {
            return true;
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    public static void removeDriver() {
        driverThread.remove();
    }
}