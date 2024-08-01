package OnlineShoppingSystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
				System.out.print("\nPlease select an option (1-3): ");

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
	
	private void loginUser() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		for (User user : store.getUsers()) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;

				System.out.println(user.getUserType() + " Logged in successfully. Welcome " + user.getName() + "!");

				return;
			}
		}
		System.out.println("Invalid username or password. Please try again.");
	}

//	private void loginUser() {
//        System.out.print("Enter user type (admin/customer): ");
//        String userType = scanner.nextLine();
//        if (userType.equalsIgnoreCase("admin")) {
//            System.out.print("Enter admin username: ");
//            String adminUsername = scanner.nextLine();
//            System.out.print("Enter admin password: ");
//            String adminPassword = scanner.nextLine();
//
//            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/ussrasrestha1/Desktop/hello.txt")))){
//                String fileUsername = fileReader.readLine();
//                String filePassword = fileReader.readLine();
//                
//                    for (User user : store.getUsers()) {
//                    	if (adminUsername.equals(fileUsername) && adminPassword.equals(filePassword)) {
//                            currentUser = user;
//                            System.out.println(user.getUserType() + " logged in successfully. Welcome " + user.getName() + "!");
//                            return;
//                        } else {
//                    System.out.println("Invalid admin credentials.");
//                }
//            }
//            }catch (IOException e) {
//                System.out.println("Error reading credentials file.");
//            }
//        } else {
//            System.out.print("Enter username: ");
//            String username = scanner.nextLine();
//            System.out.print("Enter password: ");
//            String password = scanner.nextLine();
//
//            for (User user : store.getUsers()) {
//                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
//                    currentUser = user;
//                    System.out.println(user.getUserType() + " logged in successfully. Welcome " + user.getName() + "!");
//                    return;
//                }else {
//                	System.out.println("Invalid username or password. Please try again.");
//                }
//            }
//            
//        }
//    }

//    private void loginUser() {
//        System.out.print("Enter user type (admin/customer): ");
//        String userType = scanner.nextLine();
//
//        if (userType.equalsIgnoreCase("admin")) {
//            System.out.print("Enter admin username: ");
//            String adminUsername = scanner.nextLine();
//            System.out.print("Enter admin password: ");
//            String adminPassword = scanner.nextLine();
//
//            if (validateAdminCredentials(adminUsername, adminPassword)) {
//                for (User user : store.getUsers()) {
//                    if (user.getUserName().equals(adminUsername) && user.getUserType().equalsIgnoreCase("admin")) {
//                        currentUser = user;
//                        System.out.println(user.getUserType() + " logged in successfully. Welcome " + user.getName() + "!");
//                        return;
//                    }
//                }
//            } else {
//                System.out.println("Invalid admin credentials.");
//            }
//        } else {
//            System.out.print("Enter username: ");
//            String username = scanner.nextLine();
//            System.out.print("Enter password: ");
//            String password = scanner.nextLine();
//
//            for (User user : store.getUsers()) {
//                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
//                    currentUser = user;
//                    System.out.println(user.getUserType() + " logged in successfully. Welcome " + user.getName() + "!");
//                    return;
//                }
//            }
//            System.out.println("Invalid username or password. Please try again.");
//        }
//    }
//
//    private boolean validateAdminCredentials(String username, String password) {
//        try (BufferedReader br = new BufferedReader(new FileReader("/Users/ussrasrestha1/Desktop/hello.txt"))) {
//            String fileUsername = br.readLine();
//            String filePassword = br.readLine();
//            return username.equals(fileUsername) && password.equals(filePassword);
//        } catch (IOException e) {
//            System.out.println("Error reading credentials file.");
//            return false;
//        }
//    }
	private void showAdminMenu() {
		while (currentUser != null) {
			System.out.println("Admin Menu:");
			System.out.println("------------");
			System.out.println("1. Add Product");
			System.out.println("2. Search Products");
			System.out.println("3. Update Product");
			System.out.println("4. Remove Product");
			System.out.println("5. Generate Reports");
			System.out.println("6. Logout");
			System.out.print("\nPlease select an option (1-5): ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addProduct();
				break;
			case 2:
				searchProducts();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				removeProduct();
				break;
			case 5:
				generateReports();
				break;
			case 6:
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
			System.out.println("---------------\n");
			System.out.println("1. Search Product");
			System.out.println("2. Place Order");
			System.out.println("3. Return Order");
			System.out.println("4. Logout");
			System.out.print("\nPlease select an option (1-4): ");

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

	private void addUser() {
		System.out.print("Enter full name: ");
		String name = scanner.nextLine();
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.print("Enter user type (Admin/Customer): ");
		String userType = scanner.nextLine();

		store.addUser(name, username, password, userType);
		System.out.println(userType + " registered successfully.");
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
			scanner.nextLine(); // Consume the newline character left by nextInt()
			System.out.print("Enter User Type: ");
			String userType = scanner.nextLine();
			List<Product> results1 = store.searchProductsById(productId, userType);

			// Display the results
			if (results1.isEmpty() && userType.equalsIgnoreCase("admin")) {
				System.out.println("No products found in the list.");
			} else {
				for (Product product : results1) {
					System.out.println(product);
				}
			}
			break;

		case 4:
			System.out.print("Enter product category: ");
			String productCategory = scanner.nextLine();
			results1 = store.searchProductsByCategory(productCategory);
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
			try {
				store.generateInventoryReport();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			store.generateSalesReport();
			break;
		default:
			System.out.println("Invalid option. Please try again.");
		}
	}
}
