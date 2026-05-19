package day2.firedrill2;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int sum = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Enter score " + i + ": ");
            int score = inputReader.nextInt();
            if (i % 2 == 0){
            sum+= score; 
            }
           }
            System.out.println("The sum of even index sum :" +sum);
         
            }
}

