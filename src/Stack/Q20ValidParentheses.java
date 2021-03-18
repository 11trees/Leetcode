package Stack;

import java.util.IllegalFormatException;
import java.util.Stack;

public class Q20ValidParentheses {
    /*
    Attention:
        1. 没有考虑到其他情况来优化如：奇数个符号，不能配对直接return
        2. 注意只有反括号能配对，不能把 }{ 这种形式也出栈
     */
    public static boolean validParentheses(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt( i );
            if(!stack.isEmpty() && stack.peek() == pair(ch)) stack.pop();
            else stack.push(ch);
        }
        return stack.isEmpty();
    }

    public static char pair(char ch){
        if(ch == '}') return '{';
        if(ch == ']') return '[';
        if(ch == ')') return '(';
        return '1';
    }

    public static void main(String[] args) {
        String str = "({})(";
        System.out.println(validParentheses(str));
    }
}
