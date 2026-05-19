package day6.bankApp.exceptions;
public class InsufficientBalanceException extends BankAppException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
