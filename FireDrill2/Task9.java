import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner InputReader = new Scanner(System.in);
        int sum = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Enter score :");
            int score = InputReader.nextInt();

            if (score >= 0 && score <= 100) {
            	sum+=score;

    }

}
 System.out.println("Sum of valid scores: " + sum);

}
}










