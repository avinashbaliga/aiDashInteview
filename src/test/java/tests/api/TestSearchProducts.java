package tests.api;

import objects.ManageApiObjects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;

import java.util.regex.Pattern;

public class TestSearchProducts extends BaseTest {

    private ManageApiObjects manageApiObjects;

    @BeforeClass
    public void beforeClass() {
        manageApiObjects = new ManageApiObjects();
    }

    @Test
    public void testSearchProducts() {
        info("Search all products");
        manageApiObjects.searchProductsObject().searchProducts("top");
        manageApiObjects.searchProductsObject().verifySearchProductsResponse();
    }
}
