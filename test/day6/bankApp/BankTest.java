package day6.bankApp;

import day6.bankApp.Bank;
import day6.bankApp.exceptions.BankAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank myBank;

    @BeforeEach
    public void setUp() {
        myBank = new Bank("Moniepoint Bank","50515");
    }

    @Test
    public void createAccount_test() {
        int accNo = myBank.createAccount("Alice", "1111");
        assertEquals(1, accNo);
    }

    @Test
    public void deposit_through_bank_test() {
        int accNo = myBank.createAccount("Bob", "2222");
        myBank.deposit(accNo, new BigDecimal("10000.00"));
        assertEquals(new BigDecimal("10000.00"), myBank.checkBalance(accNo, "2222"));
    }

    @Test
    public void transferBetweenAccounts_test() {
        int senderAcc = myBank.createAccount("Charlie", "3333");
        int receiverAcc = myBank.createAccount("David", "4444");

        myBank.deposit(senderAcc, new BigDecimal("5000.00"));
        myBank.transfer(senderAcc, receiverAcc, new BigDecimal("2000.00"), "3333");

        assertEquals(new BigDecimal("3000.00"), myBank.checkBalance(senderAcc, "3333"));
        assertEquals(new BigDecimal("2000.00"), myBank.checkBalance(receiverAcc, "4444"));
    }

    @Test
    public void transferWithInvalidAccount_throwsException_test() {
        int senderAcc = myBank.createAccount("Eve", "5555");
        myBank.deposit(senderAcc, new BigDecimal("1000.00"));

        assertThrows(BankAppException.class, () -> {
            myBank.transfer(senderAcc, 999, new BigDecimal("500.00"), "5555"); // 999 doesn't exist
        });
    }
}