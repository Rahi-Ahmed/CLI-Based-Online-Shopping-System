package OnlineShoppingSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a store in the online shopping system
public class Store {

	// Attributes of lists in the store class
	private List<User> users; // List of users in the store
	private List<Product> products; // List of products in the store
	private List<Order> orders; // List of orders in the store

	// Constructor to initialize lists
	public Store() {
		this.users = new ArrayList<>();
		this.products = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	// Method to add a user to the store
	public void addUser(User user) {
		users.add(user);
	}

	// Method to add an order to the store
	public void addOrder(Order order) {
		orders.add(order);
	}

	// Getter method for users
	public List<User> getUsers() {
		return users;
	}

	// Getter method for products
	public List<Product> getProducts() {
		return products;
	}

	// Getter method for orders
	public List<Order> getOrders() {
		return orders;
	}

	// Method to add a user by providing user details
	public void addUser(String name, String userName, String password, String userType) {
		// Polymorphic call to create either an Admin or Customer based on userType
		if (userType.equalsIgnoreCase("admin")) {
			users.add(new Admin(name, userName, password, userType));
		} else if (userType.equalsIgnoreCase("customer")) {
			users.add(new Customer(name, userName, password, userType));
		} else {
			System.out.println("\nPlease write either admin or customer. Thanks!!!\n");
		}
	}

	// Method to add a product to the store
	public void addProduct(Product product) {
		products.add(product);
	}

	// Method to add a product by providing product details
	public void addProduct(String productName, String productCategory, double productPrice, int productId,
			int productStock) {
		products.add(new Product(productName, productCategory, productPrice, productId, productStock));
	}
	
	private static final String PRODUCT_FILE = "/Users/ussrasrestha1/Desktop/products.txt";

	// Method to search products by name
    public List<Product> searchProductsByName(String productName) {
        List<Product> result = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length != 5) {
                    System.out.println("Invalid product format in file: " + line);
                    continue;
                }
                
                try {
                    String name = parts[0].trim();
                    String category = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int productId = Integer.parseInt(parts[3].trim());
                    int stock = Integer.parseInt(parts[4].trim());
                    
                    Product product = new Product(name, category, price, productId, stock);
                    
                    if (name.equalsIgnoreCase(productName)) {
                        result.add(product);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (result.isEmpty()) {
            System.out.println("No products found with name " + productName);
        }
        
        return result;
    }
    
    // Method to search products by price
    public List<Product> searchProductsByPrice(double maxPrice) {
        List<Product> result = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length != 5) {
                    System.out.println("Invalid product format in file: " + line);
                    continue;
                }
                
                try {
                    String name = parts[0].trim();
                    String category = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int productId = Integer.parseInt(parts[3].trim());
                    int stock = Integer.parseInt(parts[4].trim());
                    
                    Product product = new Product(name, category, price, productId, stock);
                    
                    if (price <= maxPrice) {
                        result.add(product);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (result.isEmpty()) {
            System.out.println("No products found with price up to " + maxPrice);
        }
        
        return result;
    }

	

	// Method to search products by ID, only accessible by admins
	public List<Product> searchProductsById(int productId) {
		List<Product> result = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");

				if (parts.length != 5) {
					System.out.println("Invalid product format in file: " + line);
					continue;
				}

				try {
					String productName = parts[0].trim();
					String productCategory = parts[1].trim();
					double productPrice = Double.parseDouble(parts[2].trim());
					int id = Integer.parseInt(parts[3].trim());
					int stock = Integer.parseInt(parts[4].trim());

					Product product = new Product(productName, productCategory, productPrice, id, stock);

					if (id == productId) {
						result.add(product);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid number format in file: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result.isEmpty()) {
			System.out.println("Product with ID " + productId + " not found");
		}

		return result;
	}

	// Method to search products by category
	public List<Product> searchProductsByCategory(String productCategory) {
		List<Product> result = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");

				if (parts.length != 5) {
					System.out.println("Invalid product format in file: " + line);
					continue;
				}

				try {
					String productName = parts[0].trim();
					String category = parts[1].trim();
					double productPrice = Double.parseDouble(parts[2].trim());
					int productId = Integer.parseInt(parts[3].trim());
					int stock = Integer.parseInt(parts[4].trim());

					Product product = new Product(productName, category, productPrice, productId, stock);

					if (category.equalsIgnoreCase(productCategory)) {
						result.add(product);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid number format in file: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result.isEmpty()) {
			System.out.println("No products found for category " + productCategory);
		}

		return result;
	}

	// Method to update a product's price
	public void updateProductPrice(int id, double newPrice) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setProductPrice(newPrice);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to update a product's name
	public void updateProductName(int id, String newName) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setProductName(newName);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to update a product's price and name by ID
	public void updateProductCategory(int id, String newCategory) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setProductCategory(newCategory);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to update a product's price and name by ID
	public void updateProductID(int id, int newID) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setProductId(newID);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to update a product's price and name by ID
	public void updateProductStock(int id, int newStock) {
		for (Product product : products) {
			if (product.getProductId() == id) {
				product.setStock(newStock);
				return; // Exit after updating
			}
		}
		System.out.println("Product with ID " + id + " not found.");
	}

	// Method to remove a product by ID
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

	// Scanner for user input
	Scanner scanner = new Scanner(System.in);

	public void generateInventoryReport() {
		System.out.println("Inventory Report:");
		int count = 1;
		String inputFilePath = "/Users/ussrasrestha1/Desktop/products.txt";
		String outputFilePath = "/Users/ussrasrestha1/Desktop/Inventory_Report.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
				PrintWriter fileWriter = new PrintWriter(new FileWriter(outputFilePath))) {

			String line;
			while ((line = reader.readLine()) != null) {
				fileWriter.println(count + ". " + formatProductLine(line));
				System.out.println(count + ". " + formatProductLine(line));
				count++;
			}

		} catch (IOException e) {
			System.out.println("Error during file processing: " + e.getMessage());
		}

		System.out.println("Report also saved in 'Inventory_Report.txt'");
	}

	// Helper method to format the product line
	private String formatProductLine(String line) {
		String[] parts = line.split(",");
		if (parts.length == 5) {
			String productName = parts[0];
			String productCategory = parts[1];
			String productPrice = parts[2];
			String productId = parts[3];
			String productStock = parts[4];
			return "Name: " + productName + ", Category: " + productCategory + ", Price: $" + productPrice + ", ID: "
					+ productId + ", Stock: " + productStock;
		}
		return line; // If the line format is incorrect, return it as is
	}

	// Method to generate a sales report
	public void generateSalesReport() {
		System.out.println("Sales Report:");
		int count = 1;
		try (PrintWriter fileWriter = new PrintWriter(
				new FileWriter("/Users/ussrasrestha1/Desktop/Sales_Report.txt"))) {
			for (Order order : orders) {
				fileWriter.println(count + ". " + order);
				System.out.println(count + ". " + order);
				count++;
			}
		} catch (IOException e) {
			System.out.println("Error during file writing: " + e.getMessage());
		}
		System.out.println("Report saved in 'Sales_Report.txt'");
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
