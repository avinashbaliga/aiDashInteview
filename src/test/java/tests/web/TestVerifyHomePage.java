package tests.web;

import objects.ManageUiPageObjects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class TestVerifyHomePage extends BaseTest {

    private ManageUiPageObjects manageUiPageObjects;

    @BeforeClass
    public void setUp() {
        manageUiPageObjects = new ManageUiPageObjects(getWebDriver());
    }

    @Test
    public void verifyHomePage() {
        String url = System.getProperty("url");
        String pageTitle = "Welcome to the-internet";

        info("Launching homepage: " + url);
        manageUiPageObjects.getHomePage().launchHomePage(url);
        info("Home page launched successfully");

        info("Verifying home title: " + pageTitle);
        manageUiPageObjects.getHomePage().verifyTitle(pageTitle);
        info("Title verified");
    }
}
