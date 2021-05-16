package Queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Q346MovingAverageFromDataStream {
    private static class MovingAverage {
        final int MAXSIZE;
        int currentSize = 0;
        int sum;
        Deque<Integer> deque = new LinkedList<>();

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            MAXSIZE = size;
        }

        public double next(int val) {
            if( currentSize == MAXSIZE){
                sum -= deque.removeFirst();
                deque.addLast(val);
                sum += val;
            }

            if( currentSize < MAXSIZE ){
                deque.addLast(val);
                sum += val;
                currentSize++;
            }

            return sum * 1.0 / currentSize;
        }
    }

}
