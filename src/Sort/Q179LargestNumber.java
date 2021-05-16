package Sort;

import java.util.Arrays;

public class Q179LargestNumber {
    private static class MySolution{
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for(int i = 0 ; i < nums.length ; i++){
                strs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strs);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = nums.length - 1 ; i >= 0 ; i--) stringBuilder.append(strs[i]);
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        MySolution mySolution = new MySolution();
        int[] nums = new int[]{7,30,34,5,9};
        mySolution.largestNumber(nums);
    }
}
