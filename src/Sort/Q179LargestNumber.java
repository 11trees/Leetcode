package Sort;

import java.util.Arrays;

public class Q179LargestNumber {
    private static class MySolution{
        /**
         * 思路：直接转数组进行排序，按字典序排列，则前面数字大的在前面。
         * @param nums
         * @return
         *
         * 结果会出现问题，例如对 3 ，30 。按照字典序排序 30 > 3 故结合起来是 303 ， 但实际上最大的是 330
         * 所以考虑排序时，按照结合之后的可能性的字典序进行排序。见MySolution2
         */
        @Deprecated
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

    private static class MySolution2{
        /**
         * 思路：按照数组结合后的字典序进行排序，则前面数字大的在前面。
         * @param nums
         * @return
         *
         * 该方法没有处理 "0" "0" 这样的情况。
         */
        @Deprecated
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for(int i = 0 ; i < nums.length ; i++){
                strs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strs, (a,b) -> {
                        String ab = a + b;
                        String ba = b + a;
                        return ab.compareTo(ba);
                    }
            );
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = nums.length - 1 ; i >= 0 ; i--) stringBuilder.append(strs[i]);
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        MySolution2 mySolution = new MySolution2();
        int[] nums = new int[]{3,30};
        String s = mySolution.largestNumber(nums);
        System.out.println(s);
    }
}
