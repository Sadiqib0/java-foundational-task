package day6.bankApp;

import day6.bankApp.exceptions.BankAppException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts;
    private int nextAccountNumber = 1; // Auto-incrementing account numbers

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public int createAccount(String firstName, String pin) {
        Account newAccount = new Account(nextAccountNumber, firstName, pin);
        accounts.add(newAccount);

        int assignedNumber = nextAccountNumber;
        nextAccountNumber++;
        return assignedNumber;
    }

    public void deposit(int accountNumber, BigDecimal amount) {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(int accountNumber, BigDecimal amount, String pin) {
        Account account = findAccount(accountNumber);
        account.withdraw(amount, pin);
    }

    public BigDecimal checkBalance(int accountNumber, String pin) {
        Account account = findAccount(accountNumber);
        return account.checkBalance(pin);
    }

    public void transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount, String pin) {
        Account sender = findAccount(fromAccountNumber);
        Account receiver = findAccount(toAccountNumber);

        sender.withdraw(amount, pin);
        receiver.deposit(amount);
    }


    private Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        throw new BankAppException("Account with number " + accountNumber + " not found.");
    }
}