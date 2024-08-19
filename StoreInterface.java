package OnlineShoppingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StoreInterface {

	// Attributes
	private Scanner scanner;
	private Store store;
	private User currentUser;

	// Constructor
	public StoreInterface() {
		this.scanner = new Scanner(System.in);
		this.store = new Store();
	}

	// Main menu
	public void showMainMenu() {
		while (true) {
			if (currentUser == null) {
				displayMainMenuOptions();
				try {
					int option = scanner.nextInt();
					scanner.nextLine(); // Consume the newline

					switch (option) {
					case 1 -> addUser();
					case 2 -> loginUser();
					case 3 -> exitSystem();
					default -> System.out.println("\nInvalid option. Please try again.\n");
					}
					// Handling anything is input other than integer
				} catch (InputMismatchException e) {
					System.out.println("\nHey, only integer input is allowed. Please try again!\n");
					scanner.nextLine(); // Consume the invalid input
				}
			} else {
				try {
					if (currentUser instanceof Admin) {
						showAdminMenu();
					} else if (currentUser instanceof Customer) {
						showCustomerMenu();
					}
				} catch (IOException e) {
					System.out.println("An error occurred: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	// Display main menu options
	private void displayMainMenuOptions() {
		System.out.println("Welcome to the online shopping system!");
		System.out.println("---------------------------------------");
		System.out.println("1. Register User");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.print("\nPlease select an option (1-3): ");
	}

	// Adding/registering user
	private void addUser() {
		System.out.print("Enter Full Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Username: ");
		String username = scanner.nextLine();
		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		System.out.print("Enter User Type (Admin/Customer): ");
		String userType = scanner.nextLine();

		store.addUser(name, username, password, userType);
		System.out.println(userType + " registered successfully.");
	}

	// Exit system
	private void exitSystem() {
		System.out.println("Exiting the system. Goodbye!");
		System.exit(0);
	}

	// Method for user login
	private void loginUser() {
		System.out.println("Are you an admin or customer:");
		System.out.println("1. Admin");
		System.out.println("2. Customer");
		System.out.print("Please select an option: ");

		int option = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		if (option == 1) {
			handleAdminLogin();
		} else if (option == 2) {
			handleCustomerLogin();
		} else {
			System.out.println("Invalid Input.");
		}
	}

	// Handle admin login
	private void handleAdminLogin() {
		System.out.print("Please Enter Admin Username: ");
		String adminUserName = scanner.nextLine().trim();
		System.out.print("Please Enter Admin Password: ");
		String adminPassword = scanner.nextLine().trim();

		for (User user : store.getUsers()) {
			if (user.getUserName().equals(adminUserName) && user.getPassword().equals(adminPassword)
					&& user instanceof Admin) {
				currentUser = user;
				System.out.println("Admin logged in successfully. Welcome " + user.getName() + "!");
				return;
			}
		}
		System.out.println("\nInvalid Admin Credentials.\n");
	}

	// Handle customer login
	private void handleCustomerLogin() {
		System.out.print("Enter Customer Username: ");
		String username = scanner.nextLine().trim();
		System.out.print("Enter Customer Password: ");
		String password = scanner.nextLine().trim();

		for (User user : store.getUsers()) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)
					&& user instanceof Customer) {
				currentUser = user;
				System.out.println("Customer logged in successfully. Welcome " + user.getName() + "!");
				return;
			}
		}
		System.out.println("\nInvalid Customer Credentials.\n");
	}

	// Admin menu
	private void showAdminMenu() throws IOException {
	    while (currentUser != null) {
	        try {
	            displayAdminMenuOptions();
	            int option = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline

	            switch (option) {
	                case 1 -> addProduct();
	                case 2 -> searchProducts();
	                case 3 -> updateProduct();
	                case 4 -> removeProduct();
	                case 5 -> generateReports();
	                case 6 -> logout();
	                default -> System.out.println("Invalid option. Please try again.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	            scanner.nextLine(); // Clear the invalid input from the scanner
	        }
	    }
	}


	// Display admin menu options
	private void displayAdminMenuOptions() {
		System.out.println("Admin Menu:");
		System.out.println("------------");
		System.out.println("1. Add Product");
		System.out.println("2. Search Products");
		System.out.println("3. Update Product");
		System.out.println("4. Remove Product");
		System.out.println("5. Generate Reports");
		System.out.println("6. Logout");
		System.out.print("\nPlease select an option (1-6): ");
	}

	// Customer menu
	private void showCustomerMenu() {
		while (currentUser != null) {
			displayCustomerMenuOptions();
			int option = scanner.nextInt();
			scanner.nextLine(); // Consume the newline
			try {
				switch (option) {
				case 1 -> searchProducts();
				case 2 -> placeOrder();
				case 3 -> returnOrder();
				case 4 -> listAllProducts();
				case 5 -> logout();
				default -> System.out.println("Invalid option. Please try again.");
				}
			} catch (InputMismatchException e) { // for handling exception of input anything other than integer
				System.out.println("\nHey, only integer input is allowed. Please try again!\n");
				scanner.nextLine(); // Consume the invalid input
			}
		}
	}

	// Display customer menu options
	private void displayCustomerMenuOptions() {
		System.out.println("Customer Menu:");
		System.out.println("---------------");
		System.out.println("1. Search Product");
		System.out.println("2. Place Order");
		System.out.println("3. Return Order");
		System.out.println("4. Show All The Products From The List");
		System.out.println("5. Logout");
		System.out.print("\nPlease select an option (1-5): ");
	}

	// List all products
	private void listAllProducts() {
		try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ussrasrestha1/Desktop/products.txt"))) {
			String line;
			int count = 1;
			while ((line = reader.readLine()) != null) {
				String[] productDetails = line.split(",");
				if (productDetails.length == 5) {
					String name = productDetails[0];
					String category = productDetails[1];
					double price = Double.parseDouble(productDetails[2]);
					int id = Integer.parseInt(productDetails[3]);
					int stock = Integer.parseInt(productDetails[4]);

					System.out.printf("%d. Name: %s, Category: %s, Price: $%.2f, ID: %d, Stock: %d%n", count, name,
							category, price, id, stock);
					count++;
				} else {
					System.out.println("Invalid product data: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Add a product
	private void addProduct() {
		System.out.print("Enter product name: ");
		String productName = scanner.nextLine();
		System.out.print("Enter product category: ");
		String productCategory = scanner.nextLine();
		System.out.print("Enter product price: ");
		double productPrice = scanner.nextDouble();
		System.out.print("Enter product id: ");
		int productId = scanner.nextInt();
		System.out.print("Enter product stock: ");
		int productStock = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		// Add product to the store
		store.addProduct(productName, productCategory, productPrice, productId, productStock);
		System.out.println("Product added successfully.");

		// Append product to the products.txt file
		try (FileWriter writer = new FileWriter("/Users/ussrasrestha1/Desktop/products.txt", true)) {
			writer.write(String.format("%s,%s,%.2f,%d,%d%n", productName, productCategory, productPrice, productId,
					productStock));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Update a product
	private void updateProduct() {
		System.out.print("Enter product id to update: ");
		int productId = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		System.out.println("What features you would like to update?");
		System.out.println("1. Product Price");
		System.out.println("2. Product Name");
		System.out.println("3. Product Name");
		int option = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		try {
			if (option == 1) {
				System.out.print("Enter new product price: ");
				double newPrice = scanner.nextDouble();
				scanner.nextLine(); // Consume the newline character
				store.updateProductPrice(productId, newPrice);
			} else if (option == 2) {
				System.out.print("Enter new product name: ");
				String newName = scanner.nextLine();
				store.updateProductName(productId, newName);
			} else if (option == 3) {
				System.out.print("Enter new category: ");
				String newCategory = scanner.nextLine();
				store.updateProductCategory(productId, newCategory);
			} else if (option == 4) {
				System.out.print("Enter new product ID: ");
				int newID = scanner.nextInt();
				store.updateProductID(productId, newID);
			} else if (option == 5) {
				System.out.print("Enter new amount of stocks: ");
				int newStock = scanner.nextInt();
				store.updateProductStock(productId, newStock);
			} else {
				System.out.println("Invalid option. Please try again.");
				return;
			}
			System.out.println("Product updated successfully.");

		} catch (InputMismatchException e) { // for handling exception of input anything other than integer
			System.out.println("\nHey, only integer input is allowed. Please try again!\n");
			scanner.nextLine(); // Consume the invalid input
		}
	}

	// Remove a product
	private void removeProduct() {
		System.out.print("Enter product id to remove: ");
		int productId = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		// Remove product from the store
		store.removeProduct(productId);
		System.out.println("Product removed successfully.");

		// Remove product from the products.txt file
		try {
			// Read all products from the file
			List<String> lines = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(
					new FileReader("/Users/ussrasrestha1/Desktop/products.txt"))) {
				String line;
				while ((line = reader.readLine()) != null) {
					// Keep only the lines that do not match the product ID to be removed
					String[] productDetails = line.split(",");
					int currentProductId = Integer.parseInt(productDetails[3].trim());
					if (currentProductId != productId) {
						lines.add(line);
					}
				}
			}

			// Write the filtered list back to the file
			try (FileWriter writer = new FileWriter("/Users/ussrasrestha1/Desktop/products.txt")) {
				for (String line : lines) {
					writer.write(line + System.lineSeparator());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Search products
	private void searchProducts() {
	    try {
	        System.out.println("Search Products");
	        System.out.println("1. By Name");
	        System.out.println("2. By Price");
	        System.out.println("3. By ID");
	        System.out.println("4. By Category");

	        int option = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        List<Product> results = new ArrayList<>();

	        switch (option) {
	            case 1 -> {
	                System.out.print("Enter Product Name: ");
	                String productName = scanner.nextLine();
	                results = store.searchProductsByName(productName);
	            }
	            case 2 -> {
	                System.out.print("Enter Product Price: ");
	                double productPrice = scanner.nextDouble();
	                results = store.searchProductsByPrice(productPrice);
	            }
	            case 3 -> {
	                System.out.print("Enter Product ID: ");
	                int productId = scanner.nextInt();
	                results = store.searchProductsById(productId);
	            }
	            case 4 -> {
	                System.out.print("Enter Product Category: ");
	                String productCategory = scanner.nextLine();
	                results = store.searchProductsByCategory(productCategory);
	            }
	            default -> {
	                System.out.println("Invalid option. Please try again.");
	                return;
	            }
	        }

	        displaySearchResults(results);

	    } catch (InputMismatchException e) {
	        System.out.println("Invalid input! Please enter the correct data type.");
	        scanner.nextLine(); // Clear the invalid input from the scanner
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred: " + e.getMessage());
	    }
	}

	// Display search results
	private void displaySearchResults(List<Product> results) {
		if (results.isEmpty()) {
			System.out.println("No products found.");
		} else {
			System.out.println("Search Results:");
			for (Product product : results) {
				System.out.println(product);
			}
		}
	}

	// Place an order
	private void placeOrder() {
		System.out.print("Enter customer username: ");
		String username = scanner.nextLine();
		/*
		 * This converts the collection (e.g., a List<User>) into a Stream of User
		 * objects. Streams are sequences of elements that support various methods which
		 * can be pipelined to produce the desired result.
		 */
		User customer = store.getUsers().stream()
				.filter(user -> user.getUserName().equals(username) && user instanceof Customer).findFirst()
				.orElse(null);

		if (customer == null) {
			System.out.println("Customer not found.");
			return;
		}

		List<Product> orderProducts = new ArrayList<>();
		while (true) {
			System.out.print("Enter product ID to add to order (or '0' to finish): ");
			int productId = scanner.nextInt();
			if (productId == 0) {
				break;
			}

			Product product = store.getProducts().stream().filter(p -> p.getProductId() == productId).findFirst()
					.orElse(null);

			if (product == null) {
				System.out.println("Product not found.");
			} else {
				orderProducts.add(product);
			}
		}

		if (!orderProducts.isEmpty()) {
			int orderId = store.getOrders().size() + 1;
			Order order = new Order(orderId, customer, orderProducts, "Placed");
			store.addOrder(order);
			System.out.println("Order placed successfully.");
		} else {
			System.out.println("No products added to the order.");
		}
	}

	// Return an order
	private void returnOrder() {
		System.out.print("Enter order ID to return: ");
		int orderId = scanner.nextInt();

		Order orderToReturn = store.getOrders().stream().filter(order -> order.getOrderId() == orderId).findFirst()
				.orElse(null);

		if (orderToReturn == null) {
			System.out.println("Order not found.");
		} else {
			orderToReturn.setStatus("Returned");
			store.updateOrder(orderToReturn);
			System.out.println("Order returned successfully.");
		}
	}

	// Generate reports
	private void generateReports() {
		System.out.println("Generate Reports");
		System.out.println("1. Inventory Report");
		System.out.println("2. Sales Report");

		int option = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character
		try {
			switch (option) {
			case 1 -> {
				store.generateInventoryReport();
			}
			case 2 -> store.generateSalesReport();
			default -> System.out.println("Invalid option. Please try again.");
			}
		} catch (InputMismatchException e) { // for handling exception of input anything other than integer
			System.out.println("\nHey, only integer input is allowed. Please try again!\n");
			scanner.nextLine(); // Consume the invalid input
		}
	}

	// Logout the current user
	private void logout() {
		currentUser = null;
		System.out.println("Logged out successfully.");
	}
}
