package middleware;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.CommonRestUtils;

import java.util.Map;

public class SearchProducts extends CommonRestUtils {

    private String baseUri;
    private String endPoint;
    private Response response;

    public SearchProducts() {
        baseUri = System.getProperty("getAllProductsBaseUri");
        endPoint = System.getProperty("searchProductsEndPoint");
    }

    public Response searchProducts(String productType) {
        return response = request(baseUri, endPoint, null, null, getPayload(productType), ContentType.URLENC, Method.POST);
    }

    private Map<String, String> getPayload(String productType) {
        return Map.of("search_product", productType);
    }

    public void verifySearchProductsResponse() {
        System.out.println(response.getBody().asString());
        Assert.assertEquals(200, response.statusCode());
    }
}
