package Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class JZ26ShuDeZiJieGou {
    private static class MySolution {
        /*
        思路：遍历并挨个验证，复杂度也有点高。
         */
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if( A == null || B == null ) return false;

            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addFirst(A);
            while( !queue.isEmpty() ){
                TreeNode node = queue.removeLast();
                if( node.val == B.val && isEqual( node , B ) ) return true;
                if( node.left != null )  queue.addFirst(node.left);
                if( node.right != null ) queue.addFirst(node.right);
            }
            return false;
        }

        public boolean isEqual(TreeNode A , TreeNode B){
            if( A.val != B.val ) return false;

            boolean ret = true;

            if( B.left != null ){
                if( A.left == null ) ret = false;
                else{
                    if( !isEqual( A.left , B.left) ) ret = false;
                }
            }

            if( B.right != null ){
                if( A.right == null ) ret = false;
                else{
                    if( !isEqual( A.right , B.right) ) ret = false;
                }
            }

            return ret;
        }
    }
}
