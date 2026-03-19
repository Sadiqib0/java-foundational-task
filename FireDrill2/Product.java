import java.util.Scanner;

public class Product {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Enter first number:");
        int fNum = inputReader.nextInt();
        
        System.out.println("Enter second number:");
        int sNum = inputReader.nextInt(); 
        
        int absFirst = (fNum < 0) ? (0 - fNum) : fNum; 
        int absSecond = (sNum < 0) ? (0 - sNum) : sNum;
        
        int result = 0;
        int count = absSecond;
        
        while (count > 0) {
            result = result + absFirst;
            count = count - 1; 
        }
        
        if ((fNum < 0 && sNum > 0) || (fNum > 0 && sNum < 0)) {
            result = 0 - result;
        }
        
        System.out.println("Result: " + result);
    }
}