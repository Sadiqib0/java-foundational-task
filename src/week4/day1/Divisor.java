package week4.day1;

public class Divisor {

    public static int divideNumbers(int dividend, int divisor) {
        if (divisor < 1) throw new IllegalArgumentException();

        int count = 0;
        if (dividend >= 0) {
            while (dividend >= divisor) {
                dividend -= divisor;
                count++;
            }
        } else {
            while (dividend <= -divisor) {
                dividend += divisor;
                count--;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        System.out.println(divideNumbers(-15, 2));
        System.out.println(divideNumbers(10, 2));
    }
}
