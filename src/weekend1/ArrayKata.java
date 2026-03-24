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
