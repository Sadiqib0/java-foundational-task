import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int number1 = input.nextInt();

        System.out.print("Enter second integer: ");
        int number2 = input.nextInt();

        int square1 = number1 * number1;
        int square2 = number2 * number2;
        int sumOfSquares = square1 + square2;
        int diffOfSquares = square1 - square2;

        System.out.printf("Square of %d is %d%n", number1, square1);
        System.out.printf("Square of %d is %d%n", number2, square2);
        System.out.printf("Sum of squares is %d%n", sumOfSquares);
        System.out.printf("Difference of squares is %d%n", diffOfSquares);
    }
}