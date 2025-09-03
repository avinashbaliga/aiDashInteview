package tests.api;

import objects.ManageApiObjects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class TestGetAllProducts extends BaseTest {

    private ManageApiObjects manageApiObjects;

    @BeforeClass
    public void beforeClass() {
        manageApiObjects = new ManageApiObjects();
    }

    @Test
    public void getAllProducts() {
        info("Getting all products");
        manageApiObjects.getAllProductsObject().getAllProducts();
        info("Getting all products successful");

        info("Verifying all products");
        manageApiObjects.getAllProductsObject().verifyProductExists();
        info("Verifying successful");

        info("Verifying product structure");
        warn("Please note: If the first key itself fails, the test won't proceed to verify remaining keys");
        manageApiObjects.getAllProductsObject().verifyProductsArrayStructure();
        info("Verifying product successful");

        info("Verifying all keys in the products list");
        manageApiObjects.getAllProductsObject().verifyAllKeysInProductsContainsValue();
        info("Products list verified successfully");

        info("Verify api response is received within 3 seconds");
        manageApiObjects.getAllProductsObject().verifyApiResponseTimeIsLessThan3Seconds();
        info("Response time is below 3 seconds as expected");
    }
}
