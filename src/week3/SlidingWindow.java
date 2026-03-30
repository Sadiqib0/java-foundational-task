package week3;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 5, 6, 9, 3, 2};
        int windowSum = nums[0] + nums[1] + nums[2];
        int maxSum = windowSum;
        int start = 0;

        for (int i = 3; i < nums.length; i++) {
            windowSum = windowSum + nums[i] - nums[i - 3];
            if (windowSum > maxSum) {
                maxSum = windowSum;
                start = i - 2;
            }
        }
        System.out.println("Max Sum: " + maxSum);
        System.out.println("Elements: {" + nums[start] + ", " + nums[start + 1] + ", " + nums[start + 2] + "}");
    }

    public static int getMaxSum(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int win = nums[0] + nums[1] + nums[2], max = win;
        for (int i = 3; i < nums.length; i++) {
            win += nums[i] - nums[i - 3];
            if (win > max) max = win;
        }
        return max;
    }
}
