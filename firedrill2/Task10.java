import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner InputReader = new Scanner(System.in);
        int sum = 0;
        int validCount = 0;
        
for (int i = 1; i <= 10; i++) {
	System.out.print("Enter a number:");
    int score = InputReader.nextInt();
    
    if (score >= 0 && score <= 100) {
        sum+= score;
        validCount++;
    }
}
           	double average = (double)sum/validCount;
if (validCount > 0) {
    System.out.println("Average of valid scores is: " +average);
} else {
    System.out.println("No valid scores entered.");
}
        
}
}
