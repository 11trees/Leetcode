package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q316RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s){
        int[] remainNums = new int[26];
        boolean[] isPushed = new boolean[26];
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            remainNums[ ch - 'a' ] ++;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            int index = ch - 'a';

            if( stack.isEmpty() ) {
                stack.push(ch);
                isPushed[index] = true;
                remainNums[index] --;
                continue;
            }

            if( !isPushed[ index ] ) {
                while (!stack.isEmpty() && ch < stack.peek()) {
                    if (remainNums[stack.peek() - 'a'] > 0) {
                        char popChar = stack.pop();
                        isPushed[popChar - 'a'] = false;
                        continue;
                    }
                    if (remainNums[stack.peek() - 'a'] == 0) {
                        break;
                    }
                }

                stack.push(ch);
                isPushed[index] = true;
            }
            remainNums[index] --;
        }

        StringBuilder ans = new StringBuilder();
        for(char ch : stack){
            ans.insert(0,ch);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

}
