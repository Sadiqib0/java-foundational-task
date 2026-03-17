import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner InputReader = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        while (count < 10) {
            System.out.print("Enter valid score " + (count + 1) + ": ");
            int score = InputReader.nextInt();

            if (score >= 0 && score <= 100) {
                sum += score;
                count++;
            } else {
                System.out.println("Wrong input");
            }
        }

        System.out.println("Total Sum: " + sum);
    }
}
