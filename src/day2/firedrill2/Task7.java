package day2.firedrill2;

import java.util.Scanner;
public class Task7 {
    public static void main(String[] args) {
    	Scanner InputReader= new Scanner(System.in);
        int sum = 0;
        int count = 0;
        
        for (int i = 1; i <= 10; i++) {
        System.out.println("Enter a number:");
       int score = InputReader.nextInt();
      
        if (score % 2 == 0){
            sum += score; 
            count++;
            }
           }
         
           	double average = (double)sum/count;
            System.out.println("The sum and average of the score :" +average+ " ");
            }
}

