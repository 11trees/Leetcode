package Stack;

import java.util.*;

public class Q224BasicCalculator {
    public static int basicCalculator(String s){
        List<String> polishNotation = toPolishNotation(s);
        int result = calPolishNotation(polishNotation);
        return result;
    }

    public static List<String> toPolishNotation(String s){
        //转化为逆波兰表达式
        StringBuilder stringBuilder = new StringBuilder();
        List<String> output = new ArrayList<>();
        Deque<Character> notion = new ArrayDeque<>();
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            //数字处理
            if ( ch >= '0' && ch <= '9' ){
                stringBuilder.append(ch);
                continue;
            }
            //符号处理
            if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')'){
                output.add(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }

            if ( notion.isEmpty() ){
                notion.push(ch);
                continue;
            }

            if( ch == '(' ){
                notion.push(ch);
                continue;
            }

            if ( ch == '+' || ch == '-'){
                while ( !notion.isEmpty() && ( notion.peek() == '+' || notion.peek() == '-' || notion.peek() == '*' ||notion.peek() == '/' ) ){
                    output.add(notion.pop().toString());
                }
                notion.push(ch);
                continue;
            }

            if ( ch == '*' || ch == '/'){
                while ( !notion.isEmpty() && ( notion.peek() == '*' ||notion.peek() == '/' ) ){
                    output.add(notion.pop().toString());
                }
                notion.push(ch);
                continue;
            }

            if( ch == ')'){
                while ( notion.peek() != '('){
                    output.add(notion.pop().toString());
                }
                notion.pop();
                continue;
            }
        }
        if(stringBuilder.length() != 0 ) output.add(stringBuilder.toString());
        while (!notion.isEmpty()) output.add(notion.pop().toString());
        return output;
    }

    public static boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public static int calPolishNotation(List<String> polishNotion){
        //计算逆波兰表达式的值
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = polishNotion.size();
        for (int i = 0; i < n; i++) {
            String token = polishNotion.get(i);
            if(token.equals("")) continue;

            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(basicCalculator("2*(5+5*2)/3+(6/2+8)"));
    }
}
