package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {this.val = -1;}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode NodeFromList (ArrayList<Integer> vals){
        /*-----从数组构建一般二叉树-----*/
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        /*
        * 添加一个头节点 和 数组的头节点
        * 因为基1的话，数组下标和子树关系十分方便表示。
        * 例如自身编号为 i 时
        * if ( i % 2 == 0 ) i 为 i/2 的左子树。
        * if ( i % 2 == 1 ) i 为 i/2 的右子树。
        * */
        treeNodes.add( new TreeNode() );
        vals.add(0,-1);
        for(int i = 1 ; i < vals.size() ; i++){
            if( vals.get(i) == null ) {
                treeNodes.add(null);
                continue;
            }
            TreeNode node = new TreeNode(vals.get(i) ,null , null );
            if ( i == 1) treeNodes.add(node);
            else if ( i % 2 == 0 ){
                treeNodes.get( i/2 ).left = node;
                treeNodes.add(node);
            } else if ( i % 2 == 1 ){
                treeNodes.get( i/2 ).right = node;
                treeNodes.add(node);
            }
        }
        return treeNodes.get(1);
    }

    public static ArrayList<Integer> ListFromString(String str){
        //去掉头尾的[]符号
        String string = str.substring(1 , str.length() - 1);
        String[] strings = string.split(",");
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 0 ; i < strings.length ; i++){
            if( strings[i].equals("null") ) ret.add(null);
            else ret.add(Integer.parseInt(strings[i]));
        }
        return ret;
    }

    /*----层序遍历打印----*/
    public static String levelOrder(TreeNode root) {
        StringBuilder ret = new StringBuilder();
        if (root == null) {
            return ret.toString();
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                ret.append(node.val + " ");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        String str = "[3,9,20,null,null,15,7]";
        System.out.println(TreeNode.levelOrder( NodeFromList( ListFromString( str ) ) ));
    }
}
