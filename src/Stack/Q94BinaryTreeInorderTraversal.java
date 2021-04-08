package Stack;

import java.util.ArrayList;
import java.util.List;

public class Q94BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        list = inorder(list, root);
        return list;
    }

    public List<Integer> inorder(List<Integer> list, TreeNode node){
        if(node.left != null) list.addAll(inorder(list , node.left));
        list.add(node.val);
        if(node.right != null) list.addAll(inorder(list , node.left));
        return list;
    }

    //测试代码
    public static void main(String[] args) {

    }
}
