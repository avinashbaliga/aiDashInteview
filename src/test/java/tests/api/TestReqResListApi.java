package tests.api;

import objects.ManageApiObjects;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class TestReqResListApi extends BaseTest {

    private ManageApiObjects manageApiObjects;

    @BeforeClass
    public void beforeClass() {
        manageApiObjects = new ManageApiObjects();
    }

    @Test
    public void verifyAndCreateUser() {
        String userName = "Avinash Baliga";
        boolean userPresent;
        boolean userCreated;

        info("Verifying if user is present by requesting users api");
        userPresent = manageApiObjects.getListAllUsersApi().verifyUserPresent(userName);
        info((userPresent) ? "User is found in the system. Not creating a new user" : "User is not present in the system. Creating new user, Current user list: \n" + manageApiObjects.getListAllUsersApi().getAllNames());

        if (!userPresent) {
            info("Creating user: " + userName);
            manageApiObjects.getCreateUserApi().requestCreateUser(userName);

            info("Verify user is created successfully");
            userCreated = manageApiObjects.getCreateUserApi().verifyUserCreated();
            Assert.assertTrue(userCreated, "Failed to create user. Response: "+manageApiObjects.getCreateUserApi().getResponse());
        }
    }
}
