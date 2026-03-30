package week3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWIndowTest {
    @Test
    public void testLongArray() {
        int[] nums = {20,30,90,40,60,80};

        assertEquals(190, SlidingWindow.getMaxSum(nums));
    }

    @Test
    public void testShortArray() {
        int[] nums = {1, 2};
        assertEquals(0, SlidingWindow.getMaxSum(nums));
    }
    @Test
    public void testMaximumAtStart() {
        int[] nums = {10, 10, 10, 1, 2, 3};
        assertEquals(30, SlidingWindow.getMaxSum(nums));
    }
    @Test
    public void testLowestAtStart() {
        int[] nums = {0,1,0,5,6,3};
        assertEquals(14, SlidingWindow.getMaxSum(nums));
    }
    @Test
    public void testExactArray() {
        int[] nums = {1,5,6,9,3,2};

        assertEquals(20, SlidingWindow.getMaxSum(nums));
    }

}
