import org.w3c.dom.ls.LSOutput;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < 10 ; i++ ){
            stack.addFirst(i);
        }
    }
}
