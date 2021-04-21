package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q102BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if( root == null ) return new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);
        Deque<TreeNode> nextQueue = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        while( !queue.isEmpty() ){
            TreeNode n = queue.removeLast();
            list.add(n.val);
            if( n.left != null ) nextQueue.addFirst(n.left);
            if( n.right != null ) nextQueue.addFirst(n.right);
            if( queue.isEmpty() ){
                ans.add(list);
                list = new ArrayList<>();
                queue = nextQueue;
                nextQueue = new ArrayDeque<>();
            }
        }
        return ans;
    }

}
