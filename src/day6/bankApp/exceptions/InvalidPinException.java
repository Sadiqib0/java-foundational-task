package day6.bankApp.exceptions;
public class InvalidPinException extends BankAppException {
    public InvalidPinException(String message) {
        super(message);
    }
}
