

import java.util.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) { 
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Transaction failed: Insufficient account balance.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        System.out.println("=== WELCOME TO THE ATM ===");

        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter choice (1-4): ");

            

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number (1-4).");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
        }
        sc.close(); 
    }

    private void checkBalance() {
        System.out.printf("Current Account Balance: $%.2f%n", account.getBalance());
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        if (sc.hasNextDouble()) {
            double amount = sc.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Invalid input! Please enter a numeric amount.");
            sc.next();
        }
    }

    private void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        if (sc.hasNextDouble()) {
            double amount = sc.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Invalid input! Please enter a numeric amount.");
            sc.next();
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}