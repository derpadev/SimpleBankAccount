package mybank;

import java.util.Objects;
import java.util.Scanner;
import java.util.HashMap;

/**
 * BankSystem class simulates a simple banking system that allows
 * users to create accounts, deposit, withdraw, calculate interest,
 * display account details, and remove accounts.
 * <p>
 * The system maintains a HashMap of all accounts, where the key
 * is the account holder's name and the value is the BankAccount object.
 */
public class BankSystem {

    /**
     * Stores all bank accounts. The key is the account holder's name,
     * and the value is the BankAccount object (StandardAccount or VIPAccount).
     */
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    /**
     * The main method runs the banking system menu and handles user input.
     * It allows creating accounts, depositing and withdrawing money,
     * displaying accounts, calculating interest, and removing accounts.
     * <p>
     * The menu loop continues until the user chooses to exit.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankSystem bank = new BankSystem();
        BankAccount acc = null;

        int menu;

        int accType;
        String name;
        String passcode;
        double balance;
        BankAccount user;
        double amount;
        int months;



        do {
            System.out.println(
                    "\n*** Menu ***\n" +
                            "1. Create Account\n" +
                            "2. Display\n" +
                            "3. Withdraw\n" +
                            "4. Deposit\n" +
                            "5. Display All\n" +
                            "6. Remove Account\n" +
                            "7. Calculate Interest\n" +
                            "8. Exit\n"
            );

            System.out.print("Enter your choice: ");

            menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1:
                    System.out.print(
                            "\n***Create New Account***\n" +
                                    "1. Create Standard Account\n" +
                                    "2. Create VIP Account\n" +
                                    "Enter your choice: "
                    );

                    accType = input.nextInt();
                    input.nextLine();

                    while (accType != 1 && accType != 2) {
                        System.out.println("Invalid choice!");
                        System.out.print("Enter your choice: ");
                        accType = input.nextInt();
                        input.nextLine();
                    }

                    System.out.print("Enter name: ");
                    name = input.nextLine();

                    System.out.print("Enter password: ");
                    passcode = input.nextLine();

                    if (passcode.length() != 4) {
                        System.out.println("Invalid passcode");
                        break;
                    }

                    if (accType == 1) {
                        acc = new StandardAccount(name, passcode);
                        bank.accounts.put(acc.getName(), acc);
                    }
                    if (accType == 2) {
                        acc = new VIPAccount(name, passcode);
                        bank.accounts.put(acc.getName(), acc);
                    }

                    System.out.print("Starting balance: ");
                    balance = input.nextDouble();
                    acc.setBalance(balance);

                    System.out.println("Account Created!!");

                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    name = input.nextLine();

                    if (bank.accounts.containsKey(name)) {
                        user = bank.accounts.get(name);
                        user.display();
                    } else {
                        System.out.printf("Name: %s does not exist\n", name);
                    }

                    break;
                case 3:
                    System.out.println("\n**Transaction - Withdraw**");
                    System.out.print("Enter your name: ");
                    name = input.nextLine();

                    if (bank.accounts.containsKey(name)) {
                        user = bank.accounts.get(name);

                        System.out.print("Enter your passcode: ");
                        passcode = input.nextLine();

                        if (user.validatePasscode(passcode)) {
                            System.out.print("Enter amount to withdraw: ");
                            amount = input.nextDouble();

                            if (user.getBalance() >= amount) {
                                user.withDraw(amount);
                                System.out.printf("Name: %s\n" +
                                        "Balance : %.1f\n", user.getName(), user.getBalance());
                            } else {
                                System.out.println("Not enough balance");
                            }
                        } else {
                            System.out.println("Wrong passcode");
                        }
                    } else {
                        System.out.printf("Name: %s does not exist\n", name);
                    }
                    break;
                case 4:
                    System.out.println("\n**Transaction - Deposit**");
                    System.out.print("Enter your name: ");
                    name = input.nextLine();

                    if (bank.accounts.containsKey(name)) {
                        user = bank.accounts.get(name);

                        System.out.print("Enter amount to deposit: ");
                        amount = input.nextDouble();

                        if (amount >= 0) {
                            user.deposit(amount);
                            System.out.printf("Name: %s\n" +
                                    "Balance : %.1f\n", user.getName(), user.getBalance());
                        } else {
                            System.out.println("Amount must be positive!");
                        }
                    } else {
                        System.out.printf("Name: %s does not exist\n", name);
                    }
                    break;
                case 5:

                    boolean hasStandard = false;
                    boolean hasVIP = false;

                    for (BankAccount bankAcc : bank.accounts.values()) {
                        if (bankAcc.getAccountType().equals("Standard")) {
                            hasStandard = true;
                        } else {
                            hasVIP = true;
                        }
                    }

                    if (hasStandard) {
                        System.out.println("\nStandard Account Details");
                        for (BankAccount bankAcc : bank.accounts.values()) {
                            if (bankAcc.getAccountType().equals("Standard")) {
                                bankAcc.display();
                            }
                        }
                    }
                    if (hasVIP) {
                        System.out.print("\nVIP Account Details");
                        for (BankAccount bankAcc : bank.accounts.values()) {
                            if (bankAcc.getAccountType().equals("VIP")) {
                                bankAcc.display();
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("\n**Transaction - Remove Account**");
                    System.out.print("Enter your name: ");
                    name = input.nextLine();
                    user = bank.accounts.get(name);

                    System.out.print("Enter your passcode: ");
                    passcode = input.nextLine();

                    if (user.validatePasscode(passcode)) {
                        bank.accounts.remove(name);
                        System.out.println("Account has been removed!!");
                    } else {
                        System.out.println("Wrong passcode");
                    }
                    break;
                case 7:
                    System.out.println("\n**Transaction - Calculate Interest**");
                    System.out.print("Enter your name: ");
                    name = input.nextLine();
                    user = bank.accounts.get(name);

                    System.out.print("Enter the number of months: ");

                    months = input.nextInt();

                    if (months <= 0) {
                        System.out.println("Invalid input!");
                        break;
                    }

                    if (Objects.equals(user.getAccountType(), "Standard")) {
                        double simple = user.getBalance() * user.getRate() * months;
                        double interest = Math.round(simple * 100.0) / 100;
                        System.out.printf("The expected interest is: %.2f\n", interest);
                    } else {
                        double compound = user.getBalance() * (Math.pow(1 + user.getRate(), months) - 1);
                        double interest = Math.round(compound * 100.0) / 100;
                        System.out.printf("The expected interest is: %.2f\n", interest);
                    }
                    break;
                case 8:
                    System.out.println("\nThank you for banking with us!!");
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        } while (menu != 8);
    }
}
