package week4.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

        @Test
        @DisplayName("Should sort a standard unsorted array of integers")
        void testStandardSort() {
                int[] input = {5, 3, 8, 1, 2};
                int[] expected = {1, 2, 3, 5, 8};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input, "The array should be sorted from min to max.");
        }

        @Test
        @DisplayName("Should handle an array that is already sorted")
        void testAlreadySorted() {
                int[] input = {1, 2, 3, 4, 5};
                int[] expected = {1, 2, 3, 4, 5};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }

        @Test
        @DisplayName("Should handle an array sorted in reverse order")
        void testReverseSorted() {
                int[] input = {10, 7, 5, 2, 0};
                int[] expected = {0, 2, 5, 7, 10};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }

        @Test
        @DisplayName("Should handle arrays with duplicate values")
        void testDuplicates() {
                int[] input = {4, 1, 4, 3, 1};
                int[] expected = {1, 1, 3, 4, 4};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void testNegativeNumbers() {
                int[] input = {-3, 5, -1, 0, -10};
                int[] expected = {-10, -3, -1, 0, 5};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }

        @Test
        @DisplayName("Should work with an empty array")
        void testEmptyArray() {
                int[] input = {};
                int[] expected = {};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }

        @Test
        @DisplayName("Should work with a single-element array")
        void testSingleElement() {
                int[] input = {42};
                int[] expected = {42};

                SortArray.sortarray(input);

                assertArrayEquals(expected, input);
        }
}