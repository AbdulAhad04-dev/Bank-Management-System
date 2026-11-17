import java.util.Scanner;

// ABSTRACTION
abstract class BankAccount {

    // ENCAPSULATION
    private String accountHolder;
    private String accountNumber;
    private double balance;

    static String bankName = "National Bank";

    final double MIN_BALANCE = 500;

    // CONSTRUCTOR
    public BankAccount(String accountHolder,
                       String accountNumber,
                       double balance) {

        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // GETTERS
    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // METHOD OVERLOADING
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void deposit(double amount, String description) {
        System.out.println("Description: " + description);
        deposit(amount);
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // ABSTRACT METHOD
    public abstract void accountType();

    public void displayDetails() {
        System.out.println("\nBank: " + bankName);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

// INHERITANCE
class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String name, String number,
                          double balance, double interestRate) {

        super(name, number, balance);
        this.interestRate = interestRate;
    }

    // METHOD OVERRIDING
    @Override
    public void accountType() {
        System.out.println("Account Type: Savings Account");
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        System.out.println("Interest: " + interest);
    }
}

// INHERITANCE
class CurrentAccount extends BankAccount {

    public CurrentAccount(String name, String number,
                          double balance) {

        super(name, number, balance);
    }

    // METHOD OVERRIDING
    @Override
    public void accountType() {
        System.out.println("Account Type: Current Account");
    }
}

// MAIN CLASS
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // OBJECT CREATION
        BankAccount account =
            new SavingsAccount("Ali", "12345", 10000, 5);

        int choice;

        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Display Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    account.displayDetails();
                    account.accountType();
                    break;

                case 2:
                    System.out.print("Enter Amount: ");
                    double deposit = input.nextDouble();
                    account.deposit(deposit);
                    break;

                case 3:
                    System.out.print("Enter Amount: ");
                    double withdraw = input.nextDouble();
                    account.withdraw(withdraw);
                    break;

                case 4:
                    // POLYMORPHISM
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account)
                            .calculateInterest();
                    }
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        input.close();
    }
}
