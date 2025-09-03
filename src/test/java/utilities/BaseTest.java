package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    private final Logger logger;
    private WebDriver driver = null;
    private static
    ExtentReports extentReports = null;
    private ExtentTest extentTest;

    public BaseTest() {
        logger = LogManager.getLogger(this);
    }

    @BeforeSuite
    public void setProperty() {
        initiateReporter();
        try {
            Properties apiProperties = new Properties();
            Properties webProperties = new Properties();
            Properties testConfigProperties = new Properties();

            File apiPropertiesFile = new File("src/main/resources/api.properties");
            File webProperitesFile = new File("src/main/resources/web.properties");
            File testConfigPropertiesFile = new File("src/main/resources/testConfig.properties");

            FileInputStream apiPropertiesStream = new FileInputStream(apiPropertiesFile);
            FileInputStream webPropertiesStream = new FileInputStream(webProperitesFile);
            FileInputStream testConfigPropertiesStream = new FileInputStream(testConfigPropertiesFile);

            webProperties.load(webPropertiesStream);
            apiProperties.load(apiPropertiesStream);
            testConfigProperties.load(testConfigPropertiesStream);

            setSystemProperties(webProperties);
            setSystemProperties(apiProperties);
            setSystemProperties(testConfigProperties);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @BeforeClass
    public void initiateWebDriver() {
        if (System.getProperty("executionType").equalsIgnoreCase("ui")) {
            if (driver == null) {
                WebDriverUtils webDriverUtils = new WebDriverUtils();
                driver = webDriverUtils.getWebDriver();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @BeforeMethod
    public void initiateReport(Method method) {
        extentTest = extentReports.createTest(method.getDeclaringClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod
    public void analyzeFailures(ITestResult result) {
        if (!result.isSuccess())
            extentTest.log(Status.FAIL, "Test failed: " + result.getThrowable());
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    private void setSystemProperties(Properties properties) {
        for (String property : properties.stringPropertyNames()) {
            System.setProperty(property, (String) properties.get(property));
        }
    }

    public void info(String message) {
        logger.info(message);
        extentTest.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
        extentTest.warning(message);
    }

    public void error(String message) {
        logger.error(message);
        extentTest.fail(message);
    }

    private void initiateReporter() {
        ExtentReporter reporter = new ExtentSparkReporter(new File("target/executionReport.html"));
        extentReports = new ExtentReports();
        extentReports.attachReporter((ExtentObserver) reporter);
    }

    @AfterSuite
    public void flushReport() {
        extentReports.flush();
    }
}
