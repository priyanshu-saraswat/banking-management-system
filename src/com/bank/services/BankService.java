package com.bank.services;

import com.bank.account.Account;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAccountOperationException;
import com.bank.transaction.Transaction;

import java.util.function.Consumer;

public class BankService implements AccountOperations {

    private Consumer<Transaction> logTransaction = transaction -> System.out
            .println("Transaction logged: " + transaction);

    @Override
    public void deposit(Account account, double amount) throws InvalidAccountOperationException {
        if (amount <= 0) {
            throw new InvalidAccountOperationException("Deposit amount must be greater than zero.");
        }
        account.deposit(amount); 
        Transaction transaction = new Transaction("Deposit", amount);
        account.addTransaction(transaction);
        logTransaction.accept(transaction);
    }

    @Override
    public void withdraw(Account account, double amount)
            throws InsufficientFundsException, InvalidAccountOperationException {
        if (amount <= 0) {
            throw new InvalidAccountOperationException("Withdrawal amount must be greater than zero.");
        }
        account.withdraw(amount);
        Transaction transaction = new Transaction("Withdrawal", amount);
        account.addTransaction(transaction);
        logTransaction.accept(transaction);
    }

    @Override
    public void transferFunds(Account fromAccount, Account toAccount, double amount)
            throws InsufficientFundsException, InvalidAccountOperationException {
        if (fromAccount.equals(toAccount)) {
            throw new InvalidAccountOperationException("Cannot transfer money to the same account.");
        }
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
        Transaction fromTransaction = new Transaction("Transfer Out", amount);
        Transaction toTransaction = new Transaction("Transfer In", amount);
        fromAccount.addTransaction(fromTransaction);
        toAccount.addTransaction(toTransaction);
        logTransaction.accept(fromTransaction);
        logTransaction.accept(toTransaction);
    }
}
