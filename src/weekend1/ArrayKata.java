import java.util.Arrays;

public class ArrayKata {

    public static int maximumIn(int[] numbers) {
        validateArray(numbers);
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) max = num;
        }
        return max;
    }

    public static int minimumIn(int[] numbers) {
        validateArray(numbers);
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) min = num;
        }
        return min;
    }

    public static int sumOf(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static int sumOfEvenNumbersIn(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 == 0) sum += num;
        }
        return sum;
    }

    public static int sumOfOddNumbersIn(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 != 0) sum += num;
        }
        return sum;
    }

    public static int[] maximumAndMinimumOf(int[] numbers) {
        return new int[]{minimumIn(numbers), maximumIn(numbers)};
    }

    public static int noOfOddNumbersIn(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (num % 2 != 0) count++;
        }
        return count;
    }

    public static int noOfEvenNumbersIn(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (num % 2 == 0) count++;
        }
        return count;
    }

    public static int[] evenNumbersIn(int[] numbers) {
        int count = noOfEvenNumbersIn(numbers);
        int[] evenArray = new int[count];
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                evenArray[index] = numbers[i];
                index++;
            }
        }
        return evenArray;
    }

    public static int[] oddNumbersIn(int[] numbers) {
        int count = noOfOddNumbersIn(numbers);
        int[] oddArray = new int[count];
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                oddArray[index] = numbers[i];
                index++;
            }
        }
        return oddArray;
    }

    public static int[] squareNumbersIn(int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (isSquare(numbers[i])) {
                count++;
            }
        }

        int[] squareArray = new int[count];
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (isSquare(numbers[i])) {
                squareArray[index] = numbers[i];
                index++;
            }
        }
        return squareArray;
    }

    private static void validateArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
    }

    private static boolean isSquare(int num) {
        if (num < 0) return false;
        int root = (int) Math.sqrt(num);
        return root * root == num;
    }
}
            public static void main(String[] args) {
                int[] numbers = {2, 4, 9, 7, 12, 15, 16, 1};
                System.out.println("--Array Kata Test Cases--");
                System.out.println("1. Max Element: " + ArrayKata.maximumIn(numbers));
                System.out.println("2. Min Element: " + ArrayKata.minimumIn(numbers));
                System.out.println("3. Total Sum: " + ArrayKata.sumOf(numbers));
                System.out.println("4. Sum of Evens: " + ArrayKata.sumOfEvenNumbersIn(numbers));
                System.out.println("5. Sum of Odds: " + ArrayKata.sumOfOddNumbersIn(numbers));
                System.out.println("7. Count of Odds: " + ArrayKata.noOfOddNumbersIn(numbers));
                System.out.println("8. Count of Evens: " + ArrayKata.noOfEvenNumbersIn(numbers));
                System.out.println("6. Min and Max: " + Arrays.toString(ArrayKata.maximumAndMinimumOf(numbers)));
                System.out.println("9. Even Numbers: " + Arrays.toString(ArrayKata.evenNumbersIn(numbers)));
                System.out.println("10. Odd Numbers: " + Arrays.toString(ArrayKata.oddNumbersIn(numbers)));
                System.out.println("11. Square Numbers: " + Arrays.toString(ArrayKata.squareNumbersIn(numbers)));

            }
