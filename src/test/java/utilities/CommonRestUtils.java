package utilities;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommonRestUtils {

    private Gson gson;
    private Response response = null;

    public CommonRestUtils() {
        gson = new Gson();
    }

    public Response request(String baseUri, String endPoint, Map<String, Object> header, Map<String, Object> queryParams, Object payload, ContentType contentType, Method httpMethod) {
        RequestSpecification request = RestAssured.given();
        if (baseUri == null || endPoint == null) throw new RuntimeException("BaseUri or EndPoint cannot be null");

        request.baseUri(baseUri).basePath(endPoint);
        if (header != null) request.headers(header);
        if (queryParams != null) request.queryParams(queryParams);
        if (payload != null) {
            if (contentType.equals(ContentType.JSON))
                request.body(payload);
            else if (contentType.equals(ContentType.URLENC))
                request.formParams((Map<String, ?>) payload);
            else if (contentType.equals(ContentType.MULTIPART))
                request.multiPart((File) payload);
        }
        if (contentType != null) request.contentType(contentType);

        response = request.log().all().request(httpMethod);
        return response;
    }

    public Object getJsonValue(String responseBody, String jsonPath) {
        return JsonPath.read(responseBody, jsonPath);
    }

    public Object parseResponseIntoClass(String response, Class responseClass) {
        return gson.fromJson(response, responseClass);
    }

    public long getResponseTime() {
        if (response != null)
            return response.timeIn(TimeUnit.SECONDS);
        else throw new NullPointerException("Response is null");
    }
}
