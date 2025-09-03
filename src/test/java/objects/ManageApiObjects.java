package objects;

import middleware.CreateUser;
import middleware.GetAllProducts;
import middleware.GetAllUsers;
import middleware.SearchProducts;

public class ManageApiObjects {

    private GetAllProducts getAllProducts = null;
    private SearchProducts searchProducts = null;
    private GetAllUsers getAllUsers = null;
    private CreateUser createUser = null;

    public GetAllProducts getAllProductsObject() {
        return (getAllProducts == null) ? getAllProducts = new GetAllProducts() : getAllProducts;
    }

    public SearchProducts searchProductsObject() {
        return (searchProducts == null) ? searchProducts = new SearchProducts() : searchProducts;
    }

    public GetAllUsers getListAllUsersApi() {
        return (getAllUsers == null) ? getAllUsers = new GetAllUsers() : getAllUsers;
    }

    public CreateUser getCreateUserApi() {
        return (createUser == null) ? createUser = new CreateUser() : createUser;
    }
}
