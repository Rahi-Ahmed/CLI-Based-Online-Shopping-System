package OnlineShoppingSystem;

// Class representing a product in the online shopping system
public class Product {

    // Attributes of the product
    private String productName;
    private String productCategory;
    private double productPrice;
    private int productId;
    private int stock; // Attribute for stock quantity

    // Constructor to initialize product attributes
    public Product(String productName, String productCategory, double productPrice, int productId, int stock) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productId = productId;
        this.stock = stock; // Initialize stock
    }

    // Getter method for product name
    public String getName() {
        return productName;
    }

    // Setter method for product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter method for product category
    public String getProductCategory() {
        return productCategory;
    }

    // Setter method for product category
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    // Getter method for product price
    public double getProductPrice() {
        return productPrice;
    }

    // Setter method for product price
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Getter method for product ID
    public int getProductId() {
        return productId;
    }

    // Setter method for product ID
    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter method for stock quantity
    public int getStock() {
        return stock;
    }

    // Setter method for stock quantity
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Override the toString method to provide a string representation of the Product object
    @Override
    public String toString() {
        return "Name: " + productName + ", Category: " + productCategory + ", Price: " + productPrice + ", Product ID: "
                + productId + ", Stock: " + stock;
    }
}
