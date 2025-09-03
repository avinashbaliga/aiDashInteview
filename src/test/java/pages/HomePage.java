package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.CommonWebUtils;

public class HomePage extends CommonWebUtils {

    private WebDriver driver;

    @FindBy(xpath = "//h1[@class='heading']")
    private WebElement pageHeader;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchHomePage(String url) {
        driver.get(url);
    }

    public void verifyTitle(String pageTitle) {
        Assert.assertEquals(pageTitle, getText(pageHeader));
    }

    public void clickOnAddRemoveElementOption() {
        clickOnElementWithText("Add/Remove Elements");
    }
}
