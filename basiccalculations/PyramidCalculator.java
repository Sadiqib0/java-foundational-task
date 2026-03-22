import java.util.Scanner;

public class PyramidCalculator {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        System.out.print("Enter the estimated number of stones: ");
        long numOfStones = inputReader.nextLong();

        System.out.print("Enter the average weight of each stone (in tons): ");
        double averageWeight = inputReader.nextDouble();

        System.out.print("Enter the number of years taken to build: ");
        int years = inputReader.nextInt();

       
        double totalWeight = numOfStones * averageWeight;
        double weightPerYear = totalWeight / years;
        
        double daysInBuild = years * 365.25;         
        double weightPerDay = totalWeight / daysInBuild;
        double weightPerHour = weightPerDay / 24.0;
        double weightPerMinute = weightPerHour / 60.0;
        
        System.out.printf("Weight built per year:   %.2f tons%n", weightPerYear);
        System.out.printf("Weight built per hour:   %.2f tons%n", weightPerHour);
        System.out.printf("Weight built per minute: %.2f tons%n", weightPerMinute);
        
    }
}