package OnlineShoppingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreInterface {

	private Scanner scanner;
	private Store store;
	private User currentUser;

	public StoreInterface() {
		this.scanner = new Scanner(System.in);
		this.store = new Store();
	}

	public void showMainMenu() {
		while (true) {
			if (currentUser == null) {
				System.out.println("Welcome to the online shopping system!");
				System.out.println("---------------------------------------");
				System.out.println("1. Register User");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.print("Please select an option (1-3): ");

				int option = scanner.nextInt();
				scanner.nextLine();

				switch (option) {
				case 1:
					addUser();
					break;
				case 2:
					loginUser();
					break;
				case 3:
					System.out.println("Exiting the system. Goodbye!");
					return;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} else {
				if (currentUser instanceof Admin) {
					showAdminMenu();
				} else if (currentUser instanceof Customer) {
					showCustomerMenu();
				}
			}
		}
	}

	private void showAdminMenu() {
		while (currentUser != null) {
			System.out.println("Admin Menu:");
			System.out.println("1. Add Product");
			System.out.println("2. Update Product");
			System.out.println("3. Remove Product");
			System.out.println("4. Generate Reports");
			System.out.println("5. Logout");
			System.out.print("Please select an option (1-5): ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addProduct();
				break;
			case 2:
				updateProduct();
				break;
			case 3:
				removeProduct();
				break;
			case 4:
				generateReports();
				break;
			case 5:
				currentUser = null;
				System.out.println("Logged out successfully.");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void showCustomerMenu() {
		while (currentUser != null) {
			System.out.println("Customer Menu:");
			System.out.println("1. Search Product");
			System.out.println("2. Place Order");
			System.out.println("3. Return Order");
			System.out.println("4. Logout");
			System.out.print("Please select an option (1-4): ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				searchProducts();
				break;
			case 2:
				placeOrder();
				break;
			case 3:
				returnOrder();
				break;
			case 4:
				currentUser = null;
				System.out.println("Logged out successfully.");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void loginUser() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		for (User user : store.getUsers()) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				System.out.println("Login successful. Welcome " + username + "!");
				return;
			}
		}
		System.out.println("Invalid username or password. Please try again.");
	}

	private void addUser() {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.print("Enter user type (Admin/Customer): ");
		String userType = scanner.nextLine();

		store.addUser(name, username, password, userType);
		System.out.println("User added successfully.");
	}

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

		store.addProduct(productName, productCategory, productPrice, productId, productStock);
		System.out.println("Product added successfully.");
	}

	private void updateProduct() {
		System.out.print("Enter product id to update: ");
		int productId = scanner.nextInt();
		System.out.print("Enter new product price: ");
		double newPrice = scanner.nextDouble();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new product name: ");
		String newName = scanner.nextLine();

		store.updateProduct(productId, newPrice, newName);
		System.out.println("Product updated successfully.");
	}

	private void removeProduct() {
		System.out.print("Enter product id to remove: ");
		int productId = scanner.nextInt();

		store.removeProduct(productId);
		System.out.println("Product removed successfully.");
	}

	private void searchProducts() {
		System.out.println("Search Products");
		System.out.println("1. By Name");
		System.out.println("2. By Price");
		System.out.println("3. By ID");
		System.out.println("4. By Category");

		int option = scanner.nextInt();
		scanner.nextLine();

		List<Product> results = new ArrayList<>();

		switch (option) {
		case 1:
			System.out.print("Enter product name: ");
			String productName = scanner.nextLine();
			results = store.searchProductsByName(productName);
			break;
		case 2:
			System.out.print("Enter product price: ");
			double productPrice = scanner.nextDouble();
			results = store.searchProductsByPrice(productPrice);
			break;
		case 3:
			System.out.print("Enter product ID: ");
			int productId = scanner.nextInt();
			results = store.searchProductsById(productId);
			break;
		case 4:
			System.out.print("Enter product category: ");
			String productCategory = scanner.nextLine();
			results = store.searchProductsByCategory(productCategory);
			break;
		default:
			System.out.println("Invalid option. Please try again.");
			return;
		}

		if (results.isEmpty()) {
			System.out.println("No products found.");
		} else {
			System.out.println("Search Results:");
			for (Product product : results) {
				System.out.println(product);
			}
		}
	}

	private void placeOrder() {
		System.out.print("Enter customer username: ");
		String username = scanner.nextLine();
		User customer = null;
		for (User user : store.getUsers()) {
			if (user.getUserName().equals(username) && user instanceof Customer) {
				customer = user;
				break;
			}
		}

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

			Product product = null;
			for (Product p : store.getProducts()) {
				if (p.getProductId() == productId) {
					product = p;
					break;
				}
			}

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

	private void returnOrder() {
		System.out.print("Enter order ID to return: ");
		int orderId = scanner.nextInt();

		Order orderToReturn = null;
		for (Order order : store.getOrders()) {
			if (order.getOrderId() == orderId) {
				orderToReturn = order;
				break;
			}
		}

		if (orderToReturn == null) {
			System.out.println("Order not found.");
		} else {
			orderToReturn.setStatus("Returned");
			store.updateOrder(orderToReturn);
			System.out.println("Order returned successfully.");
		}
	}

	private void generateReports() {
		System.out.println("Generate Reports");
		System.out.println("1. Inventory Report");
		System.out.println("2. Sales Report");

		int option = scanner.nextInt();
		scanner.nextLine();

		switch (option) {
		case 1:
			store.generateInventoryReport();
			break;
		case 2:
			store.generateSalesReport();
			break;
		default:
			System.out.println("Invalid option. Please try again.");
		}
	}
}
