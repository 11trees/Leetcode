package Stack;

import java.util.Stack;

public class JZ09StackToQueue {
    /*
    有点类似于“负负得正”
     */
    Stack<Integer> in;
    Stack<Integer> out;

    public JZ09StackToQueue() {
        this.in = new Stack<>();
        this.out = new Stack<>();
    }

    public void appendTail(int value) {
        this.in.push(value);
    }

    public int deleteHead() {
        if(!out.isEmpty()) return out.pop();
        while(!in.isEmpty()){
            out.push(in.pop());
        }
        if(out.isEmpty()) return -1;
        return out.pop();
    }

    //test code
    public static void main(String[] args) {

    }

}
