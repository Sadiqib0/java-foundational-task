package day6.bankApp;

import day6.bankApp.exceptions.BankAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class BanksTest {
    private Bank moniepoint;
    private Banks cbnBank;
    private Bank gtbank;
    private int gtAccount;
    private int mpAccount;

    @BeforeEach
    public void setUp() {
        cbnBank = new Banks();

        gtbank = new Bank("gtbank", "058");
        moniepoint = new Bank("moniepoint", "50515");

        cbnBank.registerBank(gtbank);
        cbnBank.registerBank(moniepoint);
        gtAccount = gtbank.createAccount("Sadiq", "1111");
        mpAccount = moniepoint.createAccount("Ibrahim", "2222");
        gtbank.deposit(gtAccount, new BigDecimal("50000.00"));
    }

    @Test
    public void check_validAccount_returnsName_test() {
        String name = cbnBank.nameEnquiry("50515", mpAccount);
        assertEquals("Ibrahim", name);
    }

    @Test
    public void checkName_invalidBank_throwsException_test() {
        assertThrows(BankAppException.class, () -> {
            cbnBank.nameEnquiry("999", mpAccount);
        });
    }

    @Test
    public void interbankTransfer_successfulTransfer_test() {
        String txId = cbnBank.interbankTransfer(
                "058", gtAccount, "50515", mpAccount, new BigDecimal("15000.00"), "1111"
        );
        assertEquals(TransactionStatus.SUCCESSFUL, cbnBank.getTransactionStatus(txId));
        assertEquals(new BigDecimal("35000.00"), gtbank.checkBalance(gtAccount, "1111"));
        assertEquals(new BigDecimal("15000.00"), moniepoint.checkBalance(mpAccount, "2222"));
    }

    @Test
    public void interbankTransfer_insufficientFunds_fails_test() {
        assertThrows(BankAppException.class, () -> {
            cbnBank.interbankTransfer("058", gtAccount, "50515", mpAccount, new BigDecimal("90000.00"), "1111");
        });
        assertEquals(new BigDecimal("50000.00"), gtbank.checkBalance(gtAccount, "1111"));
    }

    @Test
    public void interbankTransfer_invalidReceiver_reverseMoney_test() {
        assertThrows(BankAppException.class, () -> {
            cbnBank.interbankTransfer("058", gtAccount, "50515", 9999, new BigDecimal("10000.00"), "1111");
        });
        assertEquals(new BigDecimal("50000.00"), gtbank.checkBalance(gtAccount, "1111"));
    }

}
