package day6.bankApp;

import day6.bankApp.exceptions.InsufficientBalanceException;
import day6.bankApp.exceptions.InvalidAmountException;
import day6.bankApp.exceptions.InvalidPinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

    public class AccountTest {
        private Account account;

        @BeforeEach
        public void setUp() {
            account = new Account(1001, "John Doe", "1234");
        }

        @Test
        public void accountIsCreated_test() {
            assertNotNull(account);
            assertEquals(1001, account.getAccountNumber());
            assertEquals(BigDecimal.ZERO, account.checkBalance("1234"));
        }

        @Test
        public void depositAmount_balanceIncreases_test() {
            account.deposit(new BigDecimal("5000.00"));
            assertEquals(new BigDecimal("5000.00"), account.checkBalance("1234"));
        }

        @Test
        public void depositNegativeAmount_throwsException_test() {
            assertThrows(InvalidAmountException.class, () -> {
                account.deposit(new BigDecimal("-500.00"));
            });
        }

        @Test
        public void checkBalanceWithWrongPin_throwsException_test() {
            assertThrows(InvalidPinException.class, () -> {
                account.checkBalance("0000");
            });
        }

        @Test
        public void withdrawWithPin_SufficientBalance_test() {
            account.deposit(new BigDecimal("10000.00"));
            account.withdraw(new BigDecimal("4000.00"), "1234");
            assertEquals(new BigDecimal("6000.00"), account.checkBalance("1234"));
        }

        @Test
        public void withdrawWithInsufficientBalance_throwsException_test() {
            account.deposit(new BigDecimal("2000.00"));
            assertThrows(InsufficientBalanceException.class, () -> {
                account.withdraw(new BigDecimal("5000.00"), "1234");
            });
        }

        @Test
        public void updatePin_test() {
            account.updatePin("1234", "9999");
            assertThrows(InvalidPinException.class, () -> {
                account.checkBalance("1234");
            });
            assertEquals(BigDecimal.ZERO, account.checkBalance("9999"));
        }
    }
