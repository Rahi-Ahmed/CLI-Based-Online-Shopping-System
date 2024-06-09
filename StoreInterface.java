package OnlineShoppingSystem;

import java.util.List;
import java.util.Scanner;

public class StoreInterface {

	private Scanner scanner;
	private Store store;

	public StoreInterface() {
		this.scanner = new Scanner(System.in);
		this.store = new Store();
	}
	
	public void showMainMenu() {
		while(true) {
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
			    	addProduct();
			    	break;
			    	
			    case 4:
			    	addProduct();
			    	break;
			    	
			    case 5:
			    	searchProducts();
			    	break;
			}
		}
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
		
		System.out.println("Searching... ");
		
		switch (choice) {
		    case 1:
		    	System.out.print("Enter name: ");
		    	String n = scanner.nextLine();
		    	List<Product> products = store.searchProducts(n);
		    	if(products.isEmpty()) {
		    		System.out.println("No Products Found!\n");
		    	} else {
		    		System.out.println("Results: \n");
		    		int i = 1;
		    		for(Product product : products) {
		    			System.out.print(i + ". " + product + "\n");
		    			i++;
		    		}
		    	}
		}
	}

}
