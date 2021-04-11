package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Q402removeKDigits {
    public static String removeKDigit(String num, int k) {
        int alreadyMoved = 0;
        Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        for(; i < num.length() ; i++){
            if( alreadyMoved > k ) break;
            char ch = num.charAt(i);

            while( !stack.isEmpty() && ch < stack.peek() ){
                alreadyMoved++;
                if( alreadyMoved > k ) break;
                stack.pop();
            }
            stack.push(ch);
        }

        while( alreadyMoved < k ){
            stack.pop();
            alreadyMoved++;
        }

        StringBuilder ans = new StringBuilder();

        while( !stack.isEmpty() && stack.peekLast() == '0') stack.pollLast();
        while ( !stack.isEmpty() ) ans.append(stack.pollLast());

        ans.append(num.substring(i));
        if( ans.toString().equals("") ) return "0";
        return ans.toString();
    }
}
