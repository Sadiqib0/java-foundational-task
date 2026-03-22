import java.util.Scanner;

public class WorldPopulation {
    public static void main(String[] args) {
       
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Enter current world population: ");
        long population = inputReader.nextLong();

        System.out.print("Enter the annual growth rate (e.g., 1.05 for 1.05%): ");
        double growthRate = inputReader.nextDouble();

        System.out.println("\n--- Population Projections for Years ---");

        for (int year = 1; year <= 5; year++) {
          
            double increase = population * (growthRate / 100);
            population = population + (long)increase;

            System.out.println("Year " + year + ": " + population);
        }
        
    }
}