package OnlineShoppingSystem;

import java.util.List;

public class Order {
	private int orderId;
	private User customer;
	private List<Product> products;
	private String status;

	public Order(int orderId, User customer, List<Product> products, String status) {
		this.orderId = orderId;
		this.customer = customer;
		this.products = products;
		this.status = status;
	}

	// Getters and setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [Order ID=" + orderId + ", Customer=" + customer + ", Products=" + products + ", Status=" + status
				+ "]";
	}
}
