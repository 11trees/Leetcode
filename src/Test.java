import org.w3c.dom.ls.LSOutput;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = new String("ab");
        System.out.println(s1 == s2);
    }
}
