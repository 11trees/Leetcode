package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q199BinaryTreeRightSideView {
    private static class MySolution{
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            if( root == null) return ret;

            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addFirst(root);
            while( !queue.isEmpty() ){
                int size = queue.size();
                int lastOne = 0;
                for(int i = 0 ; i < size ; i++){
                    TreeNode node = queue.removeLast();
                    if( node.left != null )  queue.addFirst(node.left);
                    if( node.right != null ) queue.addFirst(node.right);
                    lastOne = node.val;
                }
                ret.add(lastOne);
            }
            return ret;
        }
    }
}
