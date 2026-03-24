public class Kata {

    public static void main(String[] args){

    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public int subtract(int num1, int num2) {
        return Math.abs(num1 - num2);
    }

    public float divide(int num1, int num2) {
            if (num2 == 0) {
                return 0;
            }
            return (float) num1 / num2;
    }

    public int factorOf(int number) {
        number = Math.abs(number);
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

    public boolean isSquare(int number) {
        if (number < 0) return false;
        int sqrt = (int) Math.sqrt(number);
        return (sqrt * sqrt == number);
    }

    public boolean isPalindrome(int number) {
        int original = number;
        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }

        return original == reversed;
    }

    public long factorialOf(int number) {
        if (number < 0) return 0;
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public long squareOf(int number) {
        return (long) number * number;
    }
}
