package day2.firedrill2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int sum = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Enter score " + i + ": ");
            int score = inputReader.nextInt();
            sum+= score; 
           }
            double average = sum / 10.0;
            
            System.out.println("The total sum is: " + sum);
            System.out.println("The average is:" +average);
         
            }
}
