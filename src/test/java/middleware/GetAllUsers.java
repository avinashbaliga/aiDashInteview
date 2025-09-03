package middleware;

import io.restassured.http.Method;
import io.restassured.response.Response;
import utilities.CommonRestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllUsers extends CommonRestUtils {

    private String baseUri = null;
    private String endPoint = null;
    private List<String> userNames = null;

    public GetAllUsers() {
        baseUri = System.getProperty("reqResBaseUri");
        endPoint = System.getProperty("getAllUsersEndPoint");
    }

    private Response requestGetAllUsers(int pageCount) {

        return request(baseUri, endPoint, null, getQueryParams(pageCount), null, null, Method.GET);
    }

    private Map<String, Object> getQueryParams(int pageCount) {
        return Map.of("page", pageCount);
    }

    public boolean verifyUserPresent(String userName) {
        List<Response> responses = new ArrayList<>();
        int totalPages;
        int pageCount = 1;

        Response response = requestGetAllUsers(pageCount);
        totalPages = setTotalPages(response);
        responses.add(response);


        while (pageCount < totalPages) {
            response = requestGetAllUsers(++pageCount);
            responses.add(response);
        }

        return checkForUserNameInResponse(responses, userName);
    }

    private boolean checkForUserNameInResponse(List<Response> responses, String userName) {
        if (responses.isEmpty()) throw new RuntimeException("Response list is empty");
        List<String> names = getNames(getFirstNamesList(responses), getLastNamesList(responses));
        return verifyUserNameExistsInList(names, userName);
    }

    private boolean verifyUserNameExistsInList(List<String> names, String userName) {
        for (String name : names) {
            if (name.equalsIgnoreCase(userName))
                return true;
        }

        return false;
    }

    private List<List<String>> getFirstNamesList(List<Response> responses) {
        List<List<String>> firstNames = new ArrayList<>();

        for (Response response : responses) {
            firstNames.add(getFirstNameFromResponse(response));
        }

        return firstNames;
    }

    private List<List<String>> getLastNamesList(List<Response> responses) {
        List<List<String>> lastNames = new ArrayList<>();

        for (Response response : responses) {
            lastNames.add(getLastNameFromResponse(response));
        }

        return lastNames;
    }

    private List<String> getNames(List<List<String>> firstNames, List<List<String>> lastNames) {
        List<String> firstName = new ArrayList<>();
        List<String> lastName = new ArrayList<>();
        userNames = new ArrayList<>();

        for (List<String> fNames : firstNames) {
            firstName.addAll(fNames);
        }

        for (List<String> lNames : lastNames) {
            lastName.addAll(lNames);
        }

        for (int counter = 0; counter < firstName.size(); counter++)
            userNames.add(firstName.get(counter) + " " + lastName.get(counter));

        return userNames;
    }

    private List<String> getFirstNameFromResponse(Response response) {
        return (List<String>) getJsonValue(response.getBody().asString(), "$.data[*].first_name");
    }

    private List<String> getLastNameFromResponse(Response response) {
        return (List<String>) getJsonValue(response.getBody().asString(), "$.data[*].last_name");
    }

    private int setTotalPages(Response response) {
        return Integer.parseInt(getJsonValue(response.getBody().asString(), "$.total_pages").toString());
    }

    public List<String> getAllNames() {
        return userNames;
    }
}
