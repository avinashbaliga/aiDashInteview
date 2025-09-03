package tests.web;

import objects.ManageUiPageObjects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class TestVerifyAddRemoveElements extends BaseTest {

    private ManageUiPageObjects manageUiPageObjects;

    @BeforeClass
    public void setup() {
        manageUiPageObjects = new ManageUiPageObjects(getWebDriver());
    }

    @Test
    public void verifyAddRemoveElementsFeature() {
        String url = System.getProperty("url");
        String elementToSelect = "Add/Remove Elements";

        info("Launch homepage");
        manageUiPageObjects.getHomePage().launchHomePage(url);
        info("Homepage launched successfully");

        info("Click on 'Add/Remove Elements'");
        manageUiPageObjects.getHomePage().clickOnAddRemoveElementOption();

        info("Verify Add/Remove elements page loaded");
        manageUiPageObjects.getAddRemoveElements().verifyAddRemoveElementsPageLoaded();
        info("Add/Remove elements page loaded successfully");
    }
}
