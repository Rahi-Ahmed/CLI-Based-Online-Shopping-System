package OnlineShoppingSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Store {

	private List<User> users;
	private List<Product> products;

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

	public List<Product> searchProductsByName(String productName) {
		// productName = productName.toLowerCase();
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			if (product.getName().equalsIgnoreCase(productName)) {
				result.add(product);
			}
		}
		return result;
	}
	
	public List<Product> searchProductsByPrice(double productPrice) {
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			if (product.getProductPrice() == productPrice) {
				result.add(product);
			}
		}
		return result;
	}

	public List<Product> searchProductsById(double productID) {
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			if (product.getProductId() == productID) {
				result.add(product);
			}
		}
		return result;
	}


	public List<Product> searchProductsByCategory(String productCategory) {
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			if (product.getProductCategory().equalsIgnoreCase(productCategory)) {
				result.add(product);
			}
		}
		return result;
	}

	public void updateProduct(int id, double newPrice, String newName) {
		 for(Product product : products) {
			 if(product.getProductId() == id) {
				 product.setProductPrice(newPrice);
				 product.setProductName(newName);
			 }
		 }
	}

	public void removeProduct(int id) {
		// products.add(newPrice, newCategory);
		if (!products.isEmpty()) {
			for (Product product : products) {
				if (product.getProductId() == id) {
					products.remove(id);
				}
			}
		} else {
			System.out.println("Product with ID " + id + " is not available!");
		}

	}

}
