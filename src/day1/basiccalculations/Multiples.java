import java.util.Scanner;

public class Multiples {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int number1 = inputReader.nextInt();
        System.out.print("Enter second integer: ");
        int number2 = inputReader.nextInt();

        int tripled = number1 * 3;
        int doubled = number2 * 2;

        if (tripled % doubled == 0) {
            System.out.printf("%d being(tripled) is a multiple of %d (doubled).%n", tripled, doubled);
        } else {
            System.out.printf("%d being (tripled) is NOT a multiple of %d (doubled).%n", tripled, doubled);
        }
    }
}