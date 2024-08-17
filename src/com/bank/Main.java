package com.bank;

import com.bank.account.Account;
import com.bank.account.CurrentAccount;
import com.bank.account.SavingsAccount;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAccountOperationException;
import com.bank.exceptions.StatementGenerationException;
import com.bank.services.BankService;
import com.bank.services.StatementService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Account> accounts = new HashMap<>();
    private static BankService bankService = new BankService();
    private static StatementService statementService = new StatementService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Generate Statement");
            System.out.println("6. Exit");

            int choice = getValidInteger(scanner, "Please enter a valid option: ");

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    handleDeposit(scanner);
                    break;
                case 3:
                    handleWithdraw(scanner);
                    break;
                case 4:
                    handleTransfer(scanner);
                    break;
                case 5:
                    generateStatements();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Creating a new account.");
        System.out.print("Enter account type (savings/current): ");
        String accountType = scanner.next();
        scanner.nextLine(); 

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String ownerName = scanner.nextLine();

        double initialBalance = getValidDouble(scanner, "Invalid balance. Please enter a valid number: ");

        Account newAccount;
        if (accountType.equalsIgnoreCase("savings")) {
            newAccount = new SavingsAccount(accountNumber, ownerName, initialBalance);
        } else if (accountType.equalsIgnoreCase("current")) {
            newAccount = new CurrentAccount(accountNumber, ownerName, initialBalance);
        } else {
            System.out.println("Invalid account type. Defaulting to Savings.");
            newAccount = new SavingsAccount(accountNumber, ownerName, initialBalance);
        }

        accounts.put(accountNumber, newAccount); 
        System.out.println("Account created successfully.");
    }

    private static void handleDeposit(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        boolean validAmount = false;
        while (!validAmount) {
            try {
                System.out.print("Enter deposit amount: ");
                double amount = getValidDouble(scanner, "Invalid amount. Please enter a valid number: ");
                bankService.deposit(account, amount);
                System.out.println("Deposit successful.");
                validAmount = true;
            } catch (InvalidAccountOperationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
    }

    private static void handleWithdraw(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        boolean validAmount = false;
        while (!validAmount) {
            try {
                System.out.print("Enter withdrawal amount: ");
                double amount = getValidDouble(scanner, "Invalid amount. Please enter a valid number: ");
                bankService.withdraw(account, amount);
                System.out.println("Withdrawal successful.");
                validAmount = true;
            } catch (InsufficientFundsException | InvalidAccountOperationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
    }

    private static void handleTransfer(Scanner scanner) {
        System.out.print("Enter source account number: ");
        String fromAccountNumber = scanner.next();
        Account fromAccount = accounts.get(fromAccountNumber);

        if (fromAccount == null) {
            System.out.println("Invalid source account number.");
            return;
        }

        System.out.print("Enter destination account number: ");
        String toAccountNumber = scanner.next();
        Account toAccount = accounts.get(toAccountNumber);

        if (toAccount == null) {
            System.out.println("Invalid destination account number.");
            return;
        }

        boolean validAmount = false;
        while (!validAmount) {
            try {
                System.out.print("Enter transfer amount: ");
                double amount = getValidDouble(scanner, "Invalid amount. Please enter a valid number: ");
                bankService.transferFunds(fromAccount, toAccount, amount);
                System.out.println("Transfer successful.");
                validAmount = true;
            } catch (InsufficientFundsException | InvalidAccountOperationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
    }

    private static void generateStatements() {
        for (Account account : accounts.values()) {
            try {
                statementService.generateStatement(account);
                System.out.println("Statement generated for account: " + account.getAccountNumber());
            } catch (StatementGenerationException e) {
                System.out.println(
                        "Error generating statement for account " + account.getAccountNumber() + ": " + e.getMessage());
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String errorMessage) {
        while (true) {
            System.out.print("Enter amount: ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid number.");
                continue;
            }

            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please enter a valid number.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static int getValidInteger(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.next();
            }
        }
    }
}
