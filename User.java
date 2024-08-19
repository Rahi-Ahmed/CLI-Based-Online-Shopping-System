package OnlineShoppingSystem;

// Abstract class representing a User
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

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and setter methods for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter methods for userType
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Override the toString method to provide a string representation of the User object
    @Override
    public String toString() {
        return "User [Name=" + name + ", Username=" + userName + ", User Type=" + userType + "]";
    }
}

// Admin class extending User class
class Admin extends User {

    // Constructor
    public Admin(String name, String userName, String password, String userType) {
        super(name, userName, password, userType);
    }

    // Override the toString method to provide a string representation of the Admin object
    @Override
    public String toString() {
        return "Admin [Name=" + getName() + ", Username=" + getUserName() + "]";
    }
}

// Customer class extending User class
class Customer extends User {

    // Constructor
    public Customer(String name, String userName, String password, String userType) {
        super(name, userName, password, userType);
    }

    // Override the toString method to provide a string representation of the Customer object
    @Override
    public String toString() {
        return "Customer [Name=" + getName() + ", Username=" + getUserName() + "]";
    }
}
