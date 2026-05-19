import java.util.Scanner;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Hello, Kindly Enter Card details to verify");

        String cardNumber = inputReader.nextLine();
        String cardType = getCardType(cardNumber);

        boolean valid = isValidCard(cardNumber);
        String validityStatus;

        if (valid) {
            validityStatus = "Valid";
        } else {
            validityStatus = "Invalid";
        }

        System.out.println("Credit Card Type: " + cardType);
        System.out.println("Credit Card Number: " + cardNumber);
        System.out.println("Credit Card Digit Length: " + cardNumber.length());
        System.out.println("Credit Card Validity Status: " + validityStatus);
    }

    public static String getCardType(String cardNumber) {

        int length = cardNumber.length();

        if (length < 13 || length > 16) {
            return "Invalid Card";
        }
        if (cardNumber.charAt(0) == '4') {
            return "Visa";
        }
        if (cardNumber.charAt(0) == '5') {
            return "MasterCard";
        }
        if (cardNumber.charAt(0) == '3' && cardNumber.charAt(1) == '7') {
            return "American Express";
        }
        if (cardNumber.charAt(0) == '6') {
            return "Discover";
        }
        return "Invalid Card";
    }

    public static boolean isValidCard(String cardNumber) {

        int length = cardNumber.length();
        if (length < 13 || length > 16) {
            return false;
        }

        int[] digits = new int[length];
        for (int i = 0; i < length; i++) {
            digits[i] = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
        }

        int sumOfDouble = 0;
        int i = length - 2;

        while (i >= 0) {
            int doubleNumber = digits[i] * 2;

            if (doubleNumber > 9) {
                int firstDigit = doubleNumber / 10;
                int secondDigit = doubleNumber % 10;
                doubleNumber = firstDigit + secondDigit;
            }

            sumOfDouble = sumOfDouble + doubleNumber;
            i = i - 2;
        }

        int sumOfOdd = 0;
        int j = length - 1;

        while (j >= 0) {
            sumOfOdd = sumOfOdd + digits[j];
            j = j - 2;
        }
        int totalSum = sumOfDouble + sumOfOdd;
        return totalSum % 10 == 0;
    }
}