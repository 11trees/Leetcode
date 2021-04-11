package Stack;

import java.util.*;

public class Q394DecodeString {
    public static int pos = 0;

    public static String DecodeString( String string ){
        LinkedList<String> stack = new LinkedList<>();
        final int size = string.length();
        while (pos < size){
            String str = string.substring(pos,pos + 1);
            if( isChar(str) ) {
                stack.push(getChar(string));
                continue;
            }

            if( isDigit(str) ){
                stack.push(getDigits(string));
                continue;
            }

            if( str.matches("\\[") ){
                stack.push("[");
                pos++;
            }

            if( str.matches("\\]") ){
                pos++;

                StringBuilder strBuilder = new StringBuilder();
                LinkedList<String> queue = new LinkedList<>();
                while ( !stack.peek().matches("\\[")) {
                    queue.addFirst(stack.pop());
                }
                stack.pop();
                for(String s : queue){
                    strBuilder.append(s);
                }

                StringBuilder repeatStr = new StringBuilder();
                int repeatTimes = Integer.parseInt( stack.pop() );
                for(int i = 0 ; i < repeatTimes ; i++){
                    repeatStr.append(strBuilder.toString());
                }

                stack.push(repeatStr.toString());
            }
        }

        StringBuilder ans = new StringBuilder();
        for(String s : stack){
            ans.insert(0,s);
        }
        return ans.toString();
    }

    public static boolean isDigit(String string){
        return string.matches("[0-9]");
    }

    public static String getDigits(String string){
        String s = string.substring(pos,pos+1);
        StringBuilder digitBuilder = new StringBuilder();
        while ( pos < string.length() && s.matches("[0-9]") ) {
            digitBuilder.append(s);
            pos++;
            if(pos < string.length() ) s = string.substring(pos,pos+1);
            else break;
        }
        return digitBuilder.toString();
    }

    public static boolean isChar(String string){
        return string.matches("[a-z]");
    }

    public static String getChar(String string){
        String s = string.substring(pos,pos+1);
        StringBuilder sirBuilder = new StringBuilder();
        while ( s.matches("[a-z]") ) {
            sirBuilder.append(s);
            pos++;
            if(pos < string.length() ) s = string.substring(pos,pos+1);
            else break;
        }
        return sirBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(DecodeString("3[a2[c]]"));
    }
}
