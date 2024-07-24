package OnlineShoppingSystem;

public abstract class User {

	// Attributes for user
	private String name;
	private String userName;
	private String password;

	// Constructor
	public User(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	// Getter and setter method
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [Name=" + name + ", Username=" + userName + "]";
	}
}

// Admin class extending user class
class Admin extends User {

	public Admin(String name, String userName, String password) {
		super(name, userName, password);
	}

	@Override
	public String toString() {
		return "Admin [Name=" + getName() + ", Username=" + getUserName() + "]";
	}
}

//Customer class extending user class
class Customer extends User {

	public Customer(String name, String userName, String password) {
		super(name, userName, password);
	}

	@Override
	public String toString() {
		return "Customer [Name=" + getName() + ", Username=" + getUserName() + "]";
	}
}
