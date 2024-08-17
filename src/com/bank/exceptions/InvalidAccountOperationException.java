package com.bank.exceptions;

public class InvalidAccountOperationException extends BankException {
    public InvalidAccountOperationException(String message) {
        super(message);
    }
}
