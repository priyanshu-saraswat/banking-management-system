package com.bank.services;

import com.bank.account.Account;
import com.bank.exceptions.*;

public interface AccountOperations {
    void deposit(Account account, double amount) throws InvalidAccountOperationException;

    void withdraw(Account account, double amount) throws InsufficientFundsException, InvalidAccountOperationException;

    void transferFunds(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException, InvalidAccountOperationException;
}
