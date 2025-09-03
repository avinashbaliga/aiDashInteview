package pojo.getAllProducts;

import java.util.List;

public class AllProducts{
	private int responseCode;
	private List<ProductsItem> products;

	public int getResponseCode(){
		return responseCode;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}