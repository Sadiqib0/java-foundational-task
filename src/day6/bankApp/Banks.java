package day6.bankApp;

import day6.bankApp.exceptions.BankAppException;
import java.math.BigDecimal;
import java.util.*;

public class Banks {
    private List<Bank> networkBanks;
    private Map<String, TransactionStatus> transactionLog;

    public Banks() {
        this.networkBanks = new ArrayList<>();
        this.transactionLog = new HashMap<>();
    }

    public void registerBank(Bank bank) {
        networkBanks.add(bank);
    }

    public String nameEnquiry(String BankCode, int AccountNumber) {
        Bank targetBank = findBankByCode(BankCode);
        return targetBank.getAccountName(AccountNumber);
    }

    public String interbankTransfer(String fromBankCode, int fromAcc, String toBankCode, int toAcc, BigDecimal amount, String pin) {
        String transactionId = UUID.randomUUID().toString();
        transactionLog.put(transactionId, TransactionStatus.PENDING);
        Bank senderBank = findBankByCode(fromBankCode);
        Bank receiverBank = findBankByCode(toBankCode);

        try {
            senderBank.withdraw(fromAcc, amount, pin);
        } catch (Exception e) {
            transactionLog.put(transactionId, TransactionStatus.FAILED);
            throw new BankAppException("Transfer failed from starting" + e.getMessage());
        }

        try {
            receiverBank.deposit(toAcc, amount);
            transactionLog.put(transactionId, TransactionStatus.SUCCESSFUL);
        } catch (Exception e) {
            processReversal(senderBank, fromAcc, amount, transactionId);
            throw new BankAppException("Transfer failed at receiving,  Funds reversed. " + e.getMessage());
        }

        return transactionId;
    }

    public TransactionStatus getTransactionStatus(String transactionId) {
        if (!transactionLog.containsKey(transactionId)) {
            throw new BankAppException("Transaction ID not found.");
        }
        return transactionLog.get(transactionId);
    }

    private void processReversal(Bank senderBank, int fromAcc, BigDecimal amount, String transactionId) {
        try {
            senderBank.deposit(fromAcc, amount);
            transactionLog.put(transactionId, TransactionStatus.REVERSED);
        } catch (Exception fatalError) {
            transactionLog.put(transactionId, TransactionStatus.FAILED);
            System.err.println("CRITICAL FAILURE: Reversal failed for Account " + fromAcc);
        }
    }

    private Bank findBankByCode(String bankCode) {
        for (Bank bank : networkBanks) {
            if (bank.getBankCode().equals(bankCode)) {
                return bank;
            }
        }
        throw new BankAppException("Routing error problem: Bank code " + bankCode + " not found.");
    }

}
