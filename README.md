# Banking Management System

The Banking Management System is a Java-based application designed to manage bank accounts, handle transactions, and generate statements. The system implements various Object-Oriented Programming (OOP) concepts such as inheritance, abstraction, and exception handling to create a robust and scalable banking application

# Components

# Account

Defines the Account class, SavingsAccount class and CheckingAccount class representing a bank account with attributes such as account number, account holder name, and balance. It includes methods for deposit, withdrawal, and retrieving account details

# Exceptions

The exceptions package includes custom exception classes like InsufficientFundsException, InvalidAccountOperationException, and StatementGenerationException. These exceptions handle specific error scenarios, ensuring that errors are managed gracefully and the application remains robust

# Services 

The BankService class is crucial for managing bank accounts and transactions. It allows for creating new accounts, performing transactions, and retrieving account information, serving as the core service layer that interacts with other components

# Transactions 

The Transaction class represents a financial transaction with details such as transaction ID, amount, type, and timestamp. It plays a key role in tracking and recording all financial activities associated with accounts

# Utils
The DateUtils class provides utility methods for handling date and time operations. This includes functionality for retrieving the current date, which is essential for timestamping transactions and generating statements

# Statement

The StatementGenerator class handles the creation and saving of account statements. It formats and outputs account activity into readable statement files, which are saved in the /statements directory for future reference

# Main
The Main class is the entry point of the application, integrating all components and starting the system. It initializes the necessary services and provides a user interface for interacting with the banking system, facilitating overall functionality

# Class Diagram

[Imgur](https://i.imgur.com/stGVOOL.png)

# Statement Output
```bash
Account Number: 65478345
Account Holder: Priyanshu Saraswat
Current Balance: ₹7250.0
Statement generated on: 17-08-2024 23:31:50

Transaction History:
Withdrawal: ₹1000.0 on 17-08-2024 23:29:13
Withdrawal: ₹1000.0 on 17-08-2024 23:29:13
Deposit: ₹2500.0 on 17-08-2024 23:29:24
Deposit: ₹2500.0 on 17-08-2024 23:29:24
Withdrawal: ₹250.0 on 17-08-2024 23:29:37
Withdrawal: ₹250.0 on 17-08-2024 23:29:37
Withdrawal: ₹500.0 on 17-08-2024 23:29:48
Withdrawal: ₹500.0 on 17-08-2024 23:29:48
Withdrawal: ₹3000.0 on 17-08-2024 23:31:11
Withdrawal: ₹3000.0 on 17-08-2024 23:31:11
Transfer Out: ₹3000.0 on 17-08-2024 23:31:11
Deposit: ₹4500.0 on 17-08-2024 23:31:48
Deposit: ₹4500.0 on 17-08-2024 23:31:48
```
# 
```bash
Account Number: 56709432
Account Holder: Aditya Nair
Current Balance: ₹8000.0
Statement generated on: 17-08-2024 23:31:50

Transaction History:
Deposit: ₹3000.0 on 17-08-2024 23:31:11
Deposit: ₹3000.0 on 17-08-2024 23:31:11
Transfer In: ₹3000.0 on 17-08-2024 23:31:11
```
# Setup and Running

1. Clone the Repository

```bash
git clone 
```

2. Navigate to the Project Directory

```bash
cd BankingManagementSystem 
```

3. Compile the Project
(Ensure you have Java installed)

```bash
git clone 
javac src/com/bank/**/*.java -d bin
```

4. Run the appliction

```bash
java com.bank.Main
```