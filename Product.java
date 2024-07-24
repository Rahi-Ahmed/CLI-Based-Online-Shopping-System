package OnlineShoppingSystem;

public class Product {

	private String productName;
	private String productCategory;
	private double productPrice;
	private int productId;
	private int stock; // New attribute for stock

	public Product(String productName, String productCategory, double productPrice, int productId, int stock) {
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productId = productId;
		this.stock = stock; // Initialize stock
	}

	public String getName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Name: " + productName + ", Category: " + productCategory + ", Price: " + productPrice + ", Product ID: "
				+ productId + ", Stock: " + stock;
	}
}
