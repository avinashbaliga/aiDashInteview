package middleware;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.getAllProducts.AllProducts;
import utilities.CommonRestUtils;

import java.util.List;
import java.util.Map;

public class GetAllProducts extends CommonRestUtils {

    private String baseUri;
    private String endPoint;
    private Response response = null;

    public GetAllProducts() {
        baseUri = System.getProperty("getAllProductsBaseUri");
        endPoint = System.getProperty("getAllProductsEndPoint");
    }

    public void getAllProducts() {
        response = request(baseUri, endPoint, null, null, null, null, Method.GET);
    }

    public void verifyProductExists() {
        int productsSize = ((List<?>) getJsonValue(response.getBody().asString(), "$.products")).size();
        Assert.assertNotEquals(0, productsSize);
    }

    public void verifyProductsArrayStructure() {
        Map<String, Object> products = (Map<String, Object>) getJsonValue(response.getBody().asString(), "$.products[0]");
        Assert.assertTrue(products.containsKey("id"));
        Assert.assertTrue(products.containsKey("name"));
        Assert.assertTrue(products.containsKey("price"));
        Assert.assertTrue(products.containsKey("brand"));
        Assert.assertTrue(products.containsKey("category"));
    }

    public void verifyAllKeysInProductsContainsValue() {
        AllProducts allProducts = (AllProducts) parseResponseIntoClass(response.getBody().asString(), AllProducts.class);
        Assert.assertFalse(allProducts.getProducts().isEmpty());
    }

    public void verifyApiResponseTimeIsLessThan3Seconds() {
        Assert.assertTrue(getResponseTime() <= 3, "Response time is greater than 3");
    }
}
