package OnlineShoppingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StoreInterface {

	private Scanner scanner;
	private Store store;

	public StoreInterface() {
		this.scanner = new Scanner(System.in);
		this.store = new Store();
	}

	LinkedList<Product> products = new LinkedList<>();

	public void showMainMenu() {
		while (true) {
			System.out.println("Welcome to the online shopping system!");
			System.out.println("---------------------------------------");
			System.out.println("1. Register User");
			System.out.println("2. Add Product");
			System.out.println("3. Update Product");
			System.out.println("4. Remove Product");
			System.out.println("5. Search Product");
			System.out.println("6. Place Order");
			System.out.println("7. Return Order");
			System.out.println("8. Generate Reports");
			System.out.println("9. Exit");
			System.out.print("Please select an option (1-9): ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addUser();
				break;

			case 2:
				addProduct();
				break;

			case 3:
				updateProduct();
				break;

			case 4:
				removeProduct();
				break;

			case 5:
				searchProducts();
				break;

			case 6:
				placeOrder();
				break;

			case 7:
				returnOrder();
				break;

			case 8:
				generateOrder();
				break;

			case 9:
				// default;
				break;
			}
		}
	}

	private void generateOrder() {
		// TODO Auto-generated method stub

	}

	private void returnOrder() {
		// TODO Auto-generated method stub

	}

	private void placeOrder() {
		// TODO Auto-generated method stub

	}

	private void removeProduct() {
		// TODO Auto-generated method stub
		System.out.print("Please Enter Product ID: ");
		int id = scanner.nextInt();
		store.removeProduct(id);

	}

	private void updateProduct() {
	    System.out.print("Please Enter Product ID: ");
	    int id = scanner.nextInt();
	    scanner.nextLine(); // Consume newline left-over

	    System.out.print("Please Enter Product New Price: ");
	    double newPrice = scanner.nextDouble();
	    scanner.nextLine(); // Consume newline left-over

	    System.out.print("Please Enter Product New Name: ");
	    String newName = scanner.nextLine();

	    store.updateProduct(id, newPrice, newName);
	}


	private void addUser() {

		System.out.println("Your First and Last Name Please: ");
		String name = scanner.nextLine();

		System.out.println("Enter an User Name Please: ");
		String userName = scanner.nextLine();

		System.out.println("Enter a Password Please: ");
		String password = scanner.nextLine();

		store.addUser(name, userName, password);
		System.out.println("User registration has completes successfully!\n");

	}

	private void addProduct() {

		System.out.print("Enter Product Name: ");
		String productName = scanner.nextLine();

		System.out.print("Enter Category: ");
		String productCategory = scanner.nextLine();

		System.out.print("Enter Price: ");
		double productPrice = scanner.nextDouble();

		System.out.print("Enter Product ID: ");
		int productId = scanner.nextInt();

		store.addProduct(productName, productCategory, productPrice, productId);
		System.out.println("Product added successfully!\n");

	}

	private void searchProducts() {
		System.out.println("\nSearch by: ");
		System.out.println("1. Name");
		System.out.println("2. Category");
		System.out.println("3. Price");
		System.out.println("4. ID");

		System.out.println("Choose an option (1-4): ");

		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1:
			System.out.print("Enter name: ");
			String name = scanner.nextLine();
			System.out.println("Searching... ");
			List<Product> products = store.searchProductsByName(name);
			if (products.isEmpty()) {
				System.out.println("No Products Found!\n");
			} else {
				System.out.println("Results: \n");
				int i = 1;
				for (Product product : products) {
					System.out.print(i + ". " + product + "\n");
					i++;
				}
			}
			break;

		case 2:
			System.out.print("Enter category: ");
			String category = scanner.nextLine();
			System.out.println("Searching... ");
			List<Product> products1 = store.searchProductsByCategory(category);
			if (products1.isEmpty()) {
				System.out.println("No Products Found!\n");
			} else {
				System.out.println("Results: ");
				int i = 1;
				for (Product product : products1) {
					System.out.print(i + ". " + product + "\n");
					i++;
				}
			}
			break;

		case 3:
			System.out.print("Enter price: ");
			double price = scanner.nextDouble();
			System.out.println("Searching... ");
			List<Product> products2 = store.searchProductsByPrice(price);
			if (products2.isEmpty()) {
				System.out.println("No Products Found!\n");
			} else {
				System.out.println("Results: \n");
				int i = 1;
				for (Product product : products2) {
					System.out.print(i + ". " + product + "\n");
					i++;
				}
			}
			break;
			
		case 4:
			System.out.print("Enter ID: ");
			int id = scanner.nextInt();
			System.out.println("Searching... ");
			List<Product> products21 = store.searchProductsByPrice(id);
			if (products21.isEmpty()) {
				System.out.println("No Products Found!\n");
			} else {
				System.out.println("Results: \n");
				int i = 1;
				for (Product product : products21) {
					System.out.print(i + ". " + product + "\n");
					i++;
				}
			}
			break;
		}
	}

}
