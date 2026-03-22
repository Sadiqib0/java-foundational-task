import java.util.Scanner;

public class Integers_largest_Smallest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter five integers: ");
        int n1 = input.nextInt();
        int n2 = input.nextInt();
        int n3 = input.nextInt();
        int n4 = input.nextInt();
        int n5 = input.nextInt();

        int largest = n1;
        int smallest = n1;

        if (n2 > largest) largest = n2;
        if (n3 > largest) largest = n3;
        if (n4 > largest) largest = n4;
        if (n5 > largest) largest = n5;

        if (n2 < smallest) smallest = n2;
        if (n3 < smallest) smallest = n3;
        if (n4 < smallest) smallest = n4;
        if (n5 < smallest) smallest = n5;

        System.out.printf("Largest: %d%nSmallest: %d%n", largest, smallest);
    }
}