package day6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

class NumberFactorsTest {

    @Test
    void testRegularCompositeNumber() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 6, 12);
        List<Integer> actual = NumberFactors.getFactors(12);
        assertEquals(expected, actual, "Should return all factors of 12 in order");
    }

    @Test
    void testPerfectSquare() {
        List<Integer> expected = Arrays.asList(1, 5, 25);
        List<Integer> actual = NumberFactors.getFactors(25);
        assertEquals(expected, actual, "Should handle perfect squares without duplicating the root");
    }

    @Test
    void testPrimeNumber() {
        List<Integer> expected = Arrays.asList(1, 7);
        List<Integer> actual = NumberFactors.getFactors(7);
        assertEquals(expected, actual, "Prime numbers should only return 1 and themselves");
    }
    @Test
    public void testGetPrimeFactorsForTwenty() {
        int input = 20;
        List<Integer> expected = Arrays.asList(2, 2, 5);
        List<Integer> actual = NumberFactors.getReverseFactors(input);
        assertEquals(expected, actual, "The factors of 20 should be [2, 2, 5]");
    }
    @Test
    public void testGetPrimeFactorsForHundred() {
        int input = 100;
        List<Integer> expected = Arrays.asList(2, 2, 5, 5);
        List<Integer> actual = NumberFactors.getReverseFactors(input);
        assertEquals(expected, actual, "The factors of 20 should be [2, 2, 5, 5]");
    }

}