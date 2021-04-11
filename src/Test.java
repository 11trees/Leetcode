import org.w3c.dom.ls.LSOutput;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (scanner.hasNext()){
            num++;
            System.out.print( "Case " + num + ": ");
            System.out.println(trans(scanner.nextLine()));
            if (num == 5){
                break;
            }
        }
    }

    public static String trans(String string){
        String strs[] = string.split(" ");

        Deque<String> stack = new ArrayDeque<>();

        //栈计算逆波兰表达式
        for(int i = strs.length - 1 ; i >= 0 ; i--){
            String s = strs[i];

            if( s.equals("+") || s.equals("-") || s.equals("*") ){
                String s1 = stack.pop();
                String s2 = stack.pop();
                if( isInt(s1) && isInt(s2) ){
                    int num1 = Integer.parseInt(s1);
                    int num2 = Integer.parseInt(s2);
                    Integer num = 0;
                    switch (s){
                        case "+":
                            num = num1 + num2;
                            break;
                        case "-":
                            num = num1 - num2;
                            break;
                        case "*":
                            num = num1 * num2;
                            break;
                        default:
                    }
                    stack.push( num.toString() );
                } else {
                    stack.push(s2);
                    stack.push(s1);
                    stack.push(s);
                }
            } else {
                stack.push(s);
            }
        }

        //出栈转化为正常字符串
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e ){
            return false;
        }
    }
}
