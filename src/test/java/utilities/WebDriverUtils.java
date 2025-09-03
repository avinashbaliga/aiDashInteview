package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtils {

    public WebDriver getWebDriver() {
        WebDriver driver = initiateWebDriver(System.getProperty("browser"));
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initiateWebDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> new EdgeDriver();
        };
    }
}
