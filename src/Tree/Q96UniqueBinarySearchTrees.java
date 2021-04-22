package Tree;

public class Q96UniqueBinarySearchTrees {
    private static class MySolution {
        /*
        思路：以i为根时，左子树序列只能是[1,i-1]，然后再求左子树的总个数，逐渐细分。
        再利用memo备忘录来优化速度。
         */
        int[][] memo;

        public int numTrees(int n) {
            final int SIZE = n;
            memo = new int[SIZE + 1][SIZE + 1];
            return numTrees(1, n);
        }

        public int numTrees(int min , int max){
            if( memo[min][max] != 0 ) return memo[min][max];
            if(min == max) return 1;

            /*---当min作为根时---*/
            int numMin = numTrees( min + 1 , max );

            /*---当max作为根时---*/
            int numMax = numTrees( min , max - 1 );

            /*---当i作为根时---*/
            int iNum = 0;
            for( int i = min + 1 ; i < max ; i++){
                int leftTypes = numTrees( min , i - 1 );
                int rightTypes = numTrees ( i + 1 , max );
                iNum += leftTypes * rightTypes;
            }

            int ret = iNum + numMin + numMax;
            memo[min][max] = ret;
            return ret;
        }
    }
}
