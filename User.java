package OnlineShoppingSystem;

public abstract class User {

	// Attributes for user
	private String name;
	private String userName;
	private String password;
	private String userType;

	// Constructor
	public User(String name, String userName, String password, String userType) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
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
	public String getUserType() {
		return userName;
	}

	public void setUserType(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [Name=" + name + ", Username=" + userName + ", User Type=" + userType + "]";
	}
}

// Admin class extending user class
class Admin extends User {

	public Admin(String name, String userName, String password, String userType) {
		super(name, userName, password, userType);
	}

	@Override
	public String toString() {
		return "Admin [Name=" + getName() + ", Username=" + getUserName() + "]";
	}
}

//Customer class extending user class
class Customer extends User {

	public Customer(String name, String userName, String password, String userType) {
		super(name, userName, password, userType);
	}

	@Override
	public String toString() {
		return "Customer [Name=" + getName() + ", Username=" + getUserName() + "]";
	}
}
