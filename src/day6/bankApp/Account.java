package day6.bankApp;

import day6.bankApp.exceptions.InsufficientBalanceException;
import day6.bankApp.exceptions.InvalidAmountException;
import day6.bankApp.exceptions.InvalidPinException;

import java.math.BigDecimal;

public class Account {
    private String name;
    private BigDecimal balance;
    private int number;
    private String pin;

    public Account(int number, String name, String pin) {
        this.number = number;
        this.name = name;
        this.pin = pin;
        this.balance = BigDecimal.ZERO; // Good practice to initialize
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount, String pin) {
        validatePin(pin);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }

        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient funds.");
        }

        this.balance = this.balance.subtract(amount);
    }

    public void updatePin(String oldPin, String newPin) {
        validatePin(oldPin);
        this.pin = newPin;
    }

    public BigDecimal checkBalance(String pin) {
        validatePin(pin);
        return this.balance;
    }

    public int getAccountNumber() {
        return this.number;
    }

    private void validatePin(String enteredPin) {
        if (!this.pin.equals(enteredPin)) {
            throw new InvalidPinException("Invalid PIN provided.");
        }
    }
}