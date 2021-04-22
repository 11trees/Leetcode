package Tree;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q103BinaryTreeZigzagLevelOrderTraversal {

    private static class Mysolution{

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();

            if( root == null ) return ret;

            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addFirst(root);
            boolean isReverse = false;
            while( !queue.isEmpty() ){
                int size = queue.size();
                List<Integer> level = new ArrayList<>();
                for( int i = 0 ; i < size ; i++ ){
                    TreeNode node = queue.removeLast();

                    if (isReverse) {
                        level.add(0, node.val );
                    } else {
                        level.add(node.val);
                    }

                    if( node.left != null ) queue.addFirst(node.left);
                    if( node.right != null ) queue.addFirst(node.right);
                }
                isReverse = !isReverse;
                ret.add(level);
            }
            return ret;
        }
    }

}

