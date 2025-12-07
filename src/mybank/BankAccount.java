package mybank;

import java.util.Objects;

/**
 * BankAccount interface defines the common operations
 * for bank accounts, such as deposit, withdraw, and balance management.
 */
public interface BankAccount  {

    /**
     * Deposit a specified amount into the account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount);

    /**
     * Set the account balance to the specified value.
     * @param b the new balance
     */
    public void setBalance(double b);

    /**
     * Get the account holder's name.
     * @return the name of the account holder
     */
    public String getName();

    /**
     * Get the type of the account (Standard or VIP).
     * @return the account type as a String
     */
    public String getAccountType();

    /**
     * Get the current balance of the account.
     * @return the account balance
     */
    public double getBalance();

    /**
     * Display account details to the console.
     */
    public void display();

    /**
     * Validate if the provided passcode matches the account's passcode.
     * @param passcode the passcode to validate
     * @return true if the passcode is correct, false otherwise
     */
    public boolean validatePasscode(String passcode);

    /**
     * Withdraw a specified amount from the account.
     * @param amount the amount to withdraw
     */
    public void withDraw(double amount);

    /**
     * Get the account's interest rate.
     * @return the interest rate as a double
     */
    public double getRate();
}

/**
 * StandardAccount class implements a standard bank account.
 */
class StandardAccount implements BankAccount {
    private String name;
    private String passcode;
    private double balance;
    private String accountType;
    private double rate = 0.003;

    /**
     * Constructor to create a StandardAccount with a name and passcode.
     * Balance is initialized to 0 by default.
     * @param name the account holder's name
     * @param passcode the 4-digit passcode for the account
     */
    public StandardAccount(String name, String passcode) {
        this.name = name;
        this.passcode = passcode;
        this.accountType = "Standard";
    }

    // Getters
    /** @return the account holder's name */
    public String getName() { return this.name; }

    /** @return the account type */
    public String getAccountType() { return this.accountType; }

    /** @return the current account balance */
    public double getBalance() { return this.balance; }

    /** @return the interest rate */
    public double getRate() { return this.rate; }

    // Setters
    /**
     * Set the account balance.
     * @param b the new balance
     */
    public void setBalance(double b) { this.balance = b; }

    /** Display the account details to the console. */
    public void display() {
        System.out.printf(
                "***Account Details***\n" +
                        "Name: %s\n" +
                        "Account Type: %s\n" +
                        "Balance: %.1f\n", this.name, this.accountType, this.balance);
    }

    /**
     * Validate the account passcode.
     * @param passcode the passcode to check
     * @return true if the passcode matches, false otherwise
     */
    public boolean validatePasscode(String passcode) {
        return Objects.equals(this.passcode, passcode);
    }

    /**
     * Withdraw a specified amount from the account.
     * @param amount the amount to withdraw
     */
    public void withDraw(double amount) {
        this.balance -= amount;
    }

    /**
     * Deposit a specified amount into the account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
    }
}

/**
 * VIPAccount class implements a VIP bank account with higher interest rate.
 */
class VIPAccount implements BankAccount {
    private String name;
    private String passcode;
    private double balance;
    private String accountType;
    private double rate = 0.008;

    /**
     * Constructor to create a VIPAccount with a name and passcode.
     * Balance is initialized to 0 by default.
     * @param name the account holder's name
     * @param passcode the 4-digit passcode for the account
     */
    public VIPAccount(String name, String passcode) {
        this.name = name;
        this.passcode = passcode;
        this.accountType = "VIP";
    }

    // Getters
    /** @return the account holder's name */
    public String getName() { return this.name; }

    /** @return the account type */
    public String getAccountType() { return this.accountType; }

    /** @return the current account balance */
    public double getBalance() { return this.balance; }

    /** @return the interest rate */
    public double getRate() { return this.rate; }

    // Setters
    /**
     * Set the account balance.
     * @param b the new balance
     */
    public void setBalance(double b) { this.balance = b; }

    /** Display the account details to the console. */
    public void display() {
        System.out.printf("\n***Account Details***\n" +
                "Name: %s\n" +
                "Account Type: %s\n" +
                "Balance: %.1f\n", this.name, this.accountType, this.balance);
    }

    /**
     * Validate the account passcode.
     * @param passcode the passcode to check
     * @return true if the passcode matches, false otherwise
     */
    public boolean validatePasscode(String passcode) {
        return Objects.equals(this.passcode, passcode);
    }

    /**
     * Withdraw a specified amount from the account.
     * @param amount the amount to withdraw
     */
    public void withDraw(double amount) {
        this.balance -= amount;
    }

    /**
     * Deposit a specified amount into the account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
    }
}
