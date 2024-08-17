package com.bank.services;

import com.bank.account.Account;
import com.bank.exceptions.StatementGenerationException;
import com.bank.transaction.Transaction;
import com.bank.utils.DateUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class StatementService implements StatementGenerator {

    @Override
    public void generateStatement(Account account) throws StatementGenerationException {
        Path directory = Paths.get("statements");
        if (Files.notExists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new StatementGenerationException("Failed to create statements directory.", e);
            }
        }

        Path path = Paths.get("statements", account.getAccountNumber() + "_statement.txt");
        String content = buildStatementContent(account);

        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            throw new StatementGenerationException(
                    "Failed to generate statement for account ID: " + account.getAccountNumber(), e);
        }
    }

    private String buildStatementContent(Account account) {
        return new StringBuilder()
                .append("Account Number: ").append(account.getAccountNumber()).append("\n")
                .append("Account Holder: ").append(account.getaccountHolderName()).append("\n")
                .append("Current Balance: ₹").append(account.getBalance()).append("\n")
                .append("Statement generated on: ").append(DateUtils.getCurrentDateTime()).append("\n\n")
                .append("Transaction History:\n")
                .append(account.getTransactions().stream()
                        .map(Transaction::toString)
                        .collect(Collectors.joining("\n")))
                .append("\n\n")
                .toString();
    }
}
