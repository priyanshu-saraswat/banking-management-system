package com.bank.account;

public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
