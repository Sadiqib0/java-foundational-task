package day2.firedrill1;

public class Task9 {
    public static void main(String[] args) {
        int totalSum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 4 == 0) {
                int sum = 0;
                int powerOfI = 1;
                for (int j = 1; j <= 5; j++) {
                    powerOfI *= i;
                    sum += powerOfI;
                }
                totalSum += sum;
            }
        }
        long square = (long) totalSum * totalSum; 
        System.out.println(square);
    }
}
