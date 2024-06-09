package OnlineShoppingSystem;

public class Product {
	
	private String productName;
	private String productCategory;
	private double productPrice;
	private int productId;
	
	
	public Product (String productName, String productCategory, double productPrice, int productId) {
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productId = productId;
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
	
	public String toString() {
		return "Name: " + productName + ", Category: " + productCategory + ", Price: " + productPrice + ", Product ID = " + productId;
	}
	

}
