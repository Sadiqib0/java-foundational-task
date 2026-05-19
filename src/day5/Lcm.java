public class Lcm {

    public static long findGCD(long... numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        long result = Math.abs(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            long a = result;
            long b = Math.abs(numbers[i]);
            while (b != 0) {
                long temp = b;
                b = a % b;
                a = temp;
            }
            result = a;
            if (result == 1) return 1;
        }
        return result;
    }

    public static long findLCM(long... numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        long result = Math.abs(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            long next = Math.abs(numbers[i]);
            if (result == 0 || next == 0) return 0;
            result = result * (next / findGCD(result, next));
        }
        return result;
    }

    public static void main(String[] args) {
    }
}