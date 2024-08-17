package com.bank.account;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
