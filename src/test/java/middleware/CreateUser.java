package middleware;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utilities.CommonRestUtils;

import java.util.Map;

public class CreateUser extends CommonRestUtils {

    private String baseUri;
    private String endPoint;
    private Response response;

    public CreateUser() {
        baseUri = System.getProperty("reqResBaseUri");
        endPoint = System.getProperty("createUserEndPoint");
    }

    public void requestCreateUser(String userName) {
        response = request(baseUri, endPoint, null, null, getPayload(userName), ContentType.JSON, Method.POST);
    }

    private Map<String, String> getPayload(String userName) {
        return Map.of("name", userName, "job", "leader");
    }

    public boolean verifyUserCreated() {
        return (response.statusCode() == 201);
    }

    public String getResponse(){
        return "Status code: "+response.statusCode() + "\nResponse body: " + response.getBody().asString();
    }
}
