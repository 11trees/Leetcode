package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q56MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (ints, t1) -> ints[0] - t1[0]);
        List<int[]> ret = new ArrayList<>();
        for(int i = 0 ; i < intervals.length ; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while( i < intervals.length - 1 && intervals[i+1][0] <= right ) {
                if( intervals[i+1][1] > right) right = intervals[i+1][1];
                i++;
            }
            int[] temp = new int[2];
            temp[0] = left;
            temp[1] = right;
            ret.add(temp);
        }
        return ret.toArray(new int[ret.size()][]);
    }


    public static void main(String[] args) {
        int[][] intervals = new int[][]{ {1,3} , {3,5} , {2,4} };
        merge(intervals);
        for(int[] arr : intervals){
            System.out.print("{");
            for( int num : arr){
                System.out.print(num + " ");
            }
            System.out.println("}");
        }
    }
}
