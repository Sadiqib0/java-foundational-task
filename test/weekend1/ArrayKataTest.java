import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayKataTest {

    private final int[] numbers = {2, 4, 9, 7, 12, 15, 16, 1};

    @Test
    @DisplayName("Find maximum value in array")
    void testMaximumIn() {
        assertEquals(16, ArrayKata.maximumIn(numbers));
    }

    @Test
    @DisplayName("Find minimum value in array")
    void testMinimumIn() {
        assertEquals(1, ArrayKata.minimumIn(numbers));
    }

    @Test
    @DisplayName("Calculate total sum of all elements")
    void testSumOf() {
        assertEquals(66, ArrayKata.sumOf(numbers));
    }

    @Test
    @DisplayName("Calculate sum of even numbers")
    void testSumOfEvenNumbersIn() {
        assertEquals(34, ArrayKata.sumOfEvenNumbersIn(numbers));
    }

    @Test
    @DisplayName("Calculate the sum of odd numbers")
    void testSumOfOddNumbersIn() {
        assertEquals(32, ArrayKata.sumOfOddNumbersIn(numbers));
    }

    @Test
    @DisplayName("Return array containing both min and max")
    void testMaximumAndMinimumOf() {
        int[] expected = {1, 16};
        assertArrayEquals(expected, ArrayKata.maximumAndMinimumOf(numbers));
    }

    @Test
    @DisplayName("Count the total number of odd integers")
    void testNoOfOddNumbersIn() {
        assertEquals(4, ArrayKata.noOfOddNumbersIn(numbers));
    }

    @Test
    @DisplayName("Count the total number of even integers")
    void testNoOfEvenNumbersIn() {
        assertEquals(4, ArrayKata.noOfEvenNumbersIn(numbers));
    }

    @Test
    @DisplayName("Return array of only even numbers")
    void testEvenNumbersIn() {
        int[] expected = {2, 4, 12, 16};
        assertArrayEquals(expected, ArrayKata.evenNumbersIn(numbers));
    }

    @Test
    @DisplayName("Return array of only odd numbers")
    void testOddNumbersIn() {
        int[] expected = {9, 7, 15, 1};
        assertArrayEquals(expected, ArrayKata.oddNumbersIn(numbers));
    }

    @Test
    @DisplayName("Return array of perfect squares")
    void testSquareNumbersIn() {
        int[] expected = {4, 9, 16, 1};
        assertArrayEquals(expected, ArrayKata.squareNumbersIn(numbers));
    }

    @Test
    @DisplayName("Throw exception when array is empty or null")
    void testValidation() {
        assertThrows(IllegalArgumentException.class, () -> ArrayKata.maximumIn(new int[]{}));
        assertThrows(IllegalArgumentException.class, () -> ArrayKata.minimumIn(null));
    }
}