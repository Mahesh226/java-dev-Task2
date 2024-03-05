import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class ATM {
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        // Add some users 
        users.put("123456", new User("123456", "1234", 1000.0));
        users.put("654321", new User("654321", "4321", 500.0));
        users.put("111222", new User("111222", "1122", 5000.0));
        users.put("333444", new User("333444", "3344", 20000.0));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (authenticate(userId, pin)) {
            System.out.println("Authentication successful!");
            currentUser = users.get(userId);
            showMainMenu();
        } else {
            System.out.println("Authentication failed.Invalid User ID or PIN.");
        }
    }

    private boolean authenticate(String userId, String pin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(pin);
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your balance is: $" + currentUser.getBalance());
    }

    private void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        if (amount > 0 && amount <= currentUser.getBalance()) {
            double newBalance = currentUser.getBalance() - amount;
            currentUser.setBalance(newBalance);
            System.out.println("Withdrawal successful. Your new balance is: $" + newBalance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void depositMoney() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        if (amount > 0) {
            double newBalance = currentUser.getBalance() + amount;
            currentUser.setBalance(newBalance);
            System.out.println("Deposit successful. Your new balance is: $" + newBalance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
