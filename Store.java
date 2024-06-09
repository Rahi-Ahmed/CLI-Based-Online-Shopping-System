package OnlineShoppingSystem;

import java.util.ArrayList;
import java.util.List;

public class Store {
	
	private List <User> users;
	private List <Product> products;
	
	
	public Store() {
		this.users = new ArrayList<>();
		this.products = new ArrayList<>();
	}
	
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void addUser(String name, String userName, String password) {
		users.add(new User(name, userName, password));
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void addProduct(String productName, String productCategory, double productPrice, int productId) {
		products.add(new Product(productName, productCategory, productPrice, productId));
	}
	
	public List<Product> searchProducts (String productName){
		productName = productName.toLowerCase();
		List<Product> result = new ArrayList<>();
		for(Product product : products) {
			if(product.getName().toLowerCase().contains(productName)) {
				result.add(product);
			}
		}
		return result;
	}

}
