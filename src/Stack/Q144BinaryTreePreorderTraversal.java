package Stack;

import java.util.ArrayList;
import java.util.List;

public class Q144BinaryTreePreorderTraversal {
    //简单分治
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return new ArrayList<Integer>();
        ans.add(root.val);
        ans.addAll(preorderTraversal(root.left));
        ans.addAll(preorderTraversal(root.right));
        return ans;
    }

    //test code
    public static void main(String[] args) {

    }
}
