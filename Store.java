package OnlineShoppingSystem;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {

	// Attributes of lists in store class
	private List<User> users;
	private List<Product> products;
	private List<Order> orders;

	// declaring arrays
	public Store() {
		this.users = new ArrayList<>();
		this.products = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	// Adding User to ArrayList
	public void addUser(User user) {
		users.add(user);
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addUser(String name, String userName, String password, String userType) {
		if (userType.equalsIgnoreCase("admin")) {
			users.add(new Admin(name, userName, password, userType));
		} else {
			users.add(new Customer(name, userName, password, userType));
		}
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public void addProduct(String productName, String productCategory, double productPrice, int productId,
			int productStock) {
		products.add(new Product(productName, productCategory, productPrice, productId, productStock));
	}

	public List<Product> searchProductsByName(String productName) {
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
			if (product.getProductPrice() <= productPrice) {
				result.add(product);
			}
		}
		return result;
	}

	public List<Product> searchProductsById(int productId, String userType) {
	    List<Product> result = new ArrayList<>();
	    
	    if ("admin".equalsIgnoreCase(userType)) {
	        for (Product product : products) {
	            if (product.getProductId() == productId) {
	                result.add(product);
	            }
	        }
	    } else {
	        // Optionally, you can throw an exception or return an empty list if the user is not an admin
	        // throw new IllegalArgumentException("Only admins can search products by ID.");
	        System.out.println("Only admins can search products by ID.");
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

	// Method to update method
	public void updateProduct(int id, double newPrice, String newName) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setProductPrice(newPrice);
				product.setProductName(newName);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to remove product
	public void removeProduct(int id) {
		Product productToRemove = null;
		for (Product product : products) {
			if (product.getProductId() == id) {
				productToRemove = product;
				break;
			}
		}
		if (productToRemove != null) {
			products.remove(productToRemove);
		} else {
			System.out.println("Product with ID " + id + " is not available!");
		}
	}
	
	Scanner scanner = new Scanner(System.in);

	// Report generation
	public void generateInventoryReport() throws FileNotFoundException, IOException {
        // Prompt for output file path
        System.out.print("Enter the path to save 'result.txt': ");
        String outputFilePath = scanner.nextLine();
        
		System.out.println("Inventory Report:");
		int count = 1;
		for (Product product : products) {
	        // Write result to a file
	        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(outputFilePath + "/Inventroy Report.txt"))) {
	        	System.out.println("\nInventory Report Generating....\n");
	            fileWriter.print(count + ". " + product);
	            count++;
	            //System.out.println(product);
	        } catch (IOException e) {
	            System.out.println("Error during file writing: " + e.getMessage());
	        }
	        System.out.println("\nReport saved in 'Inventroy Report.txt'");
		}
	}

	// Method to generate sales report
	public void generateSalesReport() {
		// Prompt for output file path
        System.out.print("Enter the path to save 'Sales Report.txt': ");
        String outputFilePath = scanner.nextLine();
        
		System.out.println("Sales Report:");
		int count = 1;
		for (Order order : orders) {
			// Write result to a file
	        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(outputFilePath + "/Sales Report.txt"))) {
	        	System.out.println("\nSales Report Generating....\n");
	            fileWriter.print(count + ". " + order);
	            //System.out.println(order);
	        } catch (IOException e) {
	            System.out.println("Error during file writing: " + e.getMessage());
	        }
	        System.out.println("\nReport saved in 'Sales Report.txt'\n");
		}
	}

	// Method to retrieve orders for a specific user
	public List<Order> getOrdersByUser(User user) {
		List<Order> userOrders = new ArrayList<>();
		for (Order order : orders) {
			if (order.getCustomer().equals(user)) {
				userOrders.add(order);
			}
		}
		return userOrders;
	}

	// Method to update an existing order
	public void updateOrder(Order updatedOrder) {
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getOrderId() == updatedOrder.getOrderId()) {
				orders.set(i, updatedOrder);
				return;
			}
		}
		System.out.println("Order with ID " + updatedOrder.getOrderId() + " not found.");
	}
}
