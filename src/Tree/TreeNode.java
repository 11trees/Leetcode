package Tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {this.val = ' ';}
    TreeNode(char val) { this.val = val; }
    TreeNode(char val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
