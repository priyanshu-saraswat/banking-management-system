package com.bank.exceptions;

public class StatementGenerationException extends BankException {
    public StatementGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
