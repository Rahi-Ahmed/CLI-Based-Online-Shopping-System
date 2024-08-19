package OnlineShoppingSystem;

import java.util.List;

// Class representing an order in the online shopping system
public class Order {
    
    // Attributes of the order
    private int orderId; // Unique ID for the order
    private User customer; // Customer who placed the order
    private List<Product> products; // List of products in the order
    private String status; // Status of the order (e.g., "Pending", "Shipped", "Delivered")

    // Constructor to initialize order attributes
    public Order(int orderId, User customer, List<Product> products, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }

    // Getter method for order ID
    public int getOrderId() {
        return orderId;
    }

    // Setter method for order ID
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Getter method for customer
    public User getCustomer() {
        return customer;
    }

    // Setter method for customer
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    // Getter method for products
    public List<Product> getProducts() {
        return products;
    }

    // Setter method for products
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Getter method for status
    public String getStatus() {
        return status;
    }

    // Setter method for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Override the toString method to provide a string representation of the Order object
    @Override
    public String toString() {
        return "Order [Order ID=" + orderId + ", Customer=" + customer + ", Products=" + products + ", Status=" + status
                + "]";
    }
}
