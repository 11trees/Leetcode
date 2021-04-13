package Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class QBuildBinaryExpressionTreeFromInfixExpression {
    public Node expTree(String s) {
        String revPolishExp = InfixToRevPol(s);
        Deque<Node> output = new ArrayDeque<>();
        for(int i = 0 ; i < revPolishExp.length() ; i++){
            char ch = revPolishExp.charAt(i);
            if( ch >= '0' && ch <= '9' ){
                Node node = new Node(ch);
                output.push(node);
                continue;
            }

            if( ch == '+' || ch =='-' || ch =='*' || ch =='/' ){
                Node node2 = output.pop();
                Node node1 = output.pop();
                Node node = new Node(ch , node1 , node2);
                output.push(node);
                continue;
            }
        }
        return output.pop();
    }

    public static String InfixToRevPol(String s){
        Deque<Character> notionStack = new ArrayDeque<>();
        StringBuilder output = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            Character ch = s.charAt(i);
            if( ch >= '0' && ch <= '9' ){
                output.append(ch);
                continue;
            }

            if( notionStack.isEmpty() ||  ch == '(' ){
                notionStack.push(ch);
                continue;
            }

            if( ch == '+' || ch == '-' ){
                while ( !notionStack.isEmpty() && ( notionStack.peek() == '+' || notionStack.peek() == '-' || notionStack.peek() == '*' || notionStack.peek() == '/') ){
                    output.append(notionStack.pop());
                }
                notionStack.push(ch);
                continue;
            }

            if( ch == '*' || ch == '/' ){
                while ( !notionStack.isEmpty() && ( notionStack.peek() == '*' || notionStack.peek() == '/') ){
                    output.append(notionStack.pop());
                }
                notionStack.push(ch);
                continue;
            }

            if( ch == ')' ){
                while ( notionStack.peek() != '(' ){
                    output.append(notionStack.pop());
                }
                notionStack.pop();
                continue;
            }
        }
        while ( !notionStack.isEmpty() ) output.append(notionStack.pop());

        return output.toString();
    }


    public static void main(String[] args) {
        System.out.println(InfixToRevPol("3+(4*5)"));
    }
}
