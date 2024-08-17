package com.bank.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; 
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return type + ": ₹" + amount + " on " + getTimestamp();
    }
}
