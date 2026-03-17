import java.util.Scanner;

public class BMI_Calculator {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Enter the weight in pounds: ");
        int weight = inputReader.nextInt();

        System.out.print("Enter the height in inches: ");
        int height = inputReader.nextInt();

        int bmi = (weight * 703) / (height * height);

        System.out.println("\nYour Body Mass Index (BMI) is: " + bmi);

        System.out.println("Underweight: less than 18.5");
        System.out.println("Normal:      between 18.5 and 24.9");
        System.out.println("Overweight:  between 25 and 29.9");
        System.out.println("Obese:       30 or greater");
    }
}
