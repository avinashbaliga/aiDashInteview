package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.CommonWebUtils;

public class AddRemoveElements extends CommonWebUtils {

    public AddRemoveElements(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h3[contains(text(), 'Add/Remove Elements')]")
    private WebElement addRemoveElements;

    public void verifyAddRemoveElementsPageLoaded() {
        String pageHeader = "Add/Remove Elements";
        Assert.assertEquals(pageHeader, getText(addRemoveElements));
    }
}
