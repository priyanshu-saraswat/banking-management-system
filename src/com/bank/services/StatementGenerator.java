package com.bank.services;

import com.bank.account.Account;
import com.bank.exceptions.StatementGenerationException;

public interface StatementGenerator {
    void generateStatement(Account account) throws StatementGenerationException;
}
