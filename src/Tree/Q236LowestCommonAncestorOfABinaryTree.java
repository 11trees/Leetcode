package Tree;

public class Q236LowestCommonAncestorOfABinaryTree {
    private static class MySolution{
        /*
        * 思路：如果一个节点的左子树包含p 右子树包含q 则是该节点。
        * 如果都在同一侧，则接着往下寻找。
        *
        * 代码逻辑有点差，反复搜索调用。能完成但是速度十分缓慢。
        * */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if( root == p || root == q) return root;
            boolean isPInLeft = root.left == null ? false : isContain(root.left, p);
            boolean isPInRight = root.right == null ? false : isContain(root.right, p);
            boolean isQInLeft = root.left == null ? false : isContain(root.left, q);
            boolean isQInRight = root.right == null ? false : isContain(root.right, q);

            if( isPInLeft && isQInRight ) return root;
            if( isPInRight && isQInLeft ) return root;
            if( isPInLeft && isQInLeft ) return lowestCommonAncestor( root.left , p , q );
            if( isPInRight && isQInRight ) return lowestCommonAncestor( root.right , p , q );
            return null;
        }

        public boolean isContain(TreeNode root , TreeNode target){
            boolean ret = false;
            if( root.val == target.val ) return true;
            else {
                if( root.left != null && isContain(root.left, target) ) ret = true;
                if( root.right != null && isContain(root.right , target) ) ret = true;
            }
            return ret;
        }
    }
}
