package objects;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class ManageUiPageObjects {

    private WebDriver driver;

    private HomePage homePage = null;
    private AddRemoveElements addRemoveElements = null;

    public ManageUiPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public AddRemoveElements getAddRemoveElements() {
        return (addRemoveElements == null) ? addRemoveElements = new AddRemoveElements(driver) : addRemoveElements;
    }

}
