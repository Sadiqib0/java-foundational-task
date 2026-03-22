import java.util.Scanner;

public class CharValue {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Enter a single character: ");

        String text = inputReader.next();
        char character = text.charAt(0);
        int numericValue = (int) character;

        System.out.println("The character '" + character + "' has the value " + numericValue);
        
    }
}