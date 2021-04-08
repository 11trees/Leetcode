package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q739DailyTemperatures {
    /*
    请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     */

    //暴力法
    public static int[] dailyTemperatures(int[] T) {
        /*
        暴力法：每次算后面第一次出现高温度时的计数。
         */
        int[] ob = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int j = i;
            int count = 0;
            while (T[j] <= T[i]) {
                j++;
                count++;
                if (j >= T.length) break;
            }
            if (j >= T.length) ob[i] = 0;
            else ob[i] = count;
        }
        return ob;
    }
    //单调栈
    public static int[] dailyTemperaturesStack(int[] T){
        /*
        观察到终止条件是：“更高的温度。”
        即在终止前温度都是单调下降的。
        充分利用该单调性。
         */
        /*
        Attention:
            1. 为了遍历到每个温度值，在出栈之后也一定要将当前温度入栈。
            2. 每次出栈前都要检查栈是否为空。
         */
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[T.length];
        for(int i = 0 ; i < T.length ; i++){
            if(stack.isEmpty() || T[i] <= T[stack.peek()]) stack.push(i);
            else {
                while ((!stack.isEmpty()) && T[i] > T[stack.peek()]){
                    int prev = stack.pop();
                    ans[prev] = i - prev;
                }
                stack.push(i);
            }
        }
        return ans;
    }

    //test code
    public static void main(String[] args) {
        System.out.println(dailyTemperaturesStack(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
    }
}
