package Tree;

import java.util.*;

public class Q102BinaryTreeLevelOrderTraversal {
    private static class Mysolution {
        public List<List<Integer>> levelOrder(TreeNode root) {
        /*
        利用两个队列来完成层序遍历。
        可以观察到每一层进队后，遍历完队列下一层的元素也都能进队。
        通过判空判断是否遍历完。
         */
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

    private static class Solution {
        /*-----官方标答-----*/
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            if (root == null) {
                return ret;
            }

            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>();
                int currentLevelSize = queue.size();
                for (int i = 1; i <= currentLevelSize; ++i) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                ret.add(level);
            }

            return ret;
        }
    /*
    即知道了每次队列里都是一层的状态，可以直接通过数量控制就行。没有必要创建两个队列判空。
    注意的细节：
        1. 先创建返回的答案变量，输入空的时候不用再new
     */
    }

}





