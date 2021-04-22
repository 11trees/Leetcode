package Tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class Q105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private static class MySolution{
        /*
        * 使用递归，每次将preoeder和inorder分割为左子树，和右子树。
        * 然后递归调用。
        * 失败：
        *   1. 每次使用Arrays.copy函数，复杂度太高，难以计算！
        * */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder.length == 0 ) return null;

            TreeNode rootNode = new TreeNode(preorder[0]);
            if( preorder.length == 1 || preorder.length == 0) return rootNode;

            int root = preorder[0];
            int rootIndex = 0;
            while( inorder[rootIndex] != root ) rootIndex++;
            int[] leftInorder = Arrays.copyOfRange( inorder , 0 , rootIndex);
            int[] rightInorder = Arrays.copyOfRange( inorder , rootIndex + 1 , inorder.length);
            int[] leftPreorder = Arrays.copyOfRange( preorder , 1 ,  leftInorder.length + 1 );
            int[] rightPreorder = Arrays.copyOfRange( preorder , leftInorder.length + 1 ,  preorder.length );


            rootNode.left  = buildTree( leftPreorder  , leftInorder);
            rootNode.right = buildTree( rightPreorder  , rightInorder);

            return rootNode;
        }
    }

    private static class MySolution1{
        /*
         * 修改：通过数组下标来重复使用同一个数组，而不必每次都进行copy
         * PS:
         *  1. 也会超出时间限制。每一次搜索都几乎遍历列表，时间缓慢。可以采用hash表存储，来节省时间。
         * */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTreeDetailed(preorder,inorder,0,preorder.length - 1,0,inorder.length - 1);
        }

        public TreeNode buildTreeDetailed(int [] preorder , int[] inorder , int preorderLeft , int preorderRight , int inorderLeft , int inorderRight){
            //越界检查
            if( inorderLeft < 0 || inorderRight >= inorder.length || inorderLeft > inorderRight) return null;

            TreeNode rootNode = new TreeNode( preorder[preorderLeft] );
            //base condition
            if( preorderLeft == preorderRight ) return rootNode;

            //Recur
                int root = preorder[preorderLeft];
                int rootIndex = 0;

                while( inorder[rootIndex] != root ) rootIndex++;
                int leftSubTreeInorderLeft = inorderLeft;
                int leftSubTreeInorderRight = rootIndex - 1;
                int rightSubTreeInorderLeft = rootIndex + 1;
                int rightSubTreeInorderRight = inorderRight;

                int leftSubTreeSize = rootIndex - inorderLeft;
                int rightSubTreeSize = inorderRight - rootIndex;

                int leftSubTreePreorderLeft = preorderLeft + 1;
                int leftSubTreePreorderRight = preorderLeft + leftSubTreeSize;
                int rightSubTreePreorderLeft = leftSubTreePreorderRight + 1;
                int rightSubTreePreorderRight = leftSubTreePreorderRight + rightSubTreeSize;


                rootNode.left  = buildTreeDetailed(preorder , inorder , leftSubTreePreorderLeft , leftSubTreePreorderRight , leftSubTreeInorderLeft , leftSubTreeInorderRight);
                rootNode.right = buildTreeDetailed(preorder , inorder , rightSubTreePreorderLeft , rightSubTreePreorderRight , rightSubTreeInorderLeft , rightSubTreeInorderRight);

            return rootNode;
        }
    }

    private static class MySolution2{
        /*
         * 修改：加入hash表
         * */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTreeDetailed(buildHashMap(inorder) , preorder,inorder,0,preorder.length - 1,0,inorder.length - 1);
        }

        public TreeNode buildTreeDetailed(HashMap<Integer,Integer> hashMap , int [] preorder , int[] inorder , int preorderLeft , int preorderRight , int inorderLeft , int inorderRight){
            //越界检查
            if( inorderLeft < 0 || inorderRight >= inorder.length || inorderLeft > inorderRight) return null;

            TreeNode rootNode = new TreeNode( preorder[preorderLeft] );
            //base condition
            if( preorderLeft == preorderRight ) return rootNode;

            //Recur
            int root = preorder[preorderLeft];
            int rootIndex = hashMap.get(root);
            int leftSubTreeInorderLeft = inorderLeft;
            int leftSubTreeInorderRight = rootIndex - 1;
            int rightSubTreeInorderLeft = rootIndex + 1;
            int rightSubTreeInorderRight = inorderRight;

            int leftSubTreeSize = rootIndex - inorderLeft;
            int rightSubTreeSize = inorderRight - rootIndex;

            int leftSubTreePreorderLeft = preorderLeft + 1;
            int leftSubTreePreorderRight = preorderLeft + leftSubTreeSize;
            int rightSubTreePreorderLeft = leftSubTreePreorderRight + 1;
            int rightSubTreePreorderRight = leftSubTreePreorderRight + rightSubTreeSize;


            rootNode.left  = buildTreeDetailed(hashMap , preorder , inorder , leftSubTreePreorderLeft , leftSubTreePreorderRight , leftSubTreeInorderLeft , leftSubTreeInorderRight);
            rootNode.right = buildTreeDetailed(hashMap , preorder , inorder , rightSubTreePreorderLeft , rightSubTreePreorderRight , rightSubTreeInorderLeft , rightSubTreeInorderRight);

            return rootNode;
        }

        public HashMap<Integer,Integer> buildHashMap(int[] inorder){
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            for(int i = 0 ; i < inorder.length ; i++){
                hashMap.put(inorder[i],i);
            }
            return hashMap;
        }
    }

    @Test
    public void testMySolution(){
        System.out.println(new MySolution2().buildTree(
                new int[] {-77,24,-74,84,93,28,83,6,95,58,59,66,22,-3,-66,-68,-22,3,-80,-79,-85,17,32,9,-88,-99,14,-60,13,-93,-63,91,82,21,26,-11,-32,-16,-100,-94,-31,-62,-89,49,-9,-8,87,-33,-81,80,0,69,-7,52,67,-5,-65,31,-30,37,-57,27,23,38,-28,7,-82,-42,11,-55,-36,-58,-24,89,56,73,41,18,-87,-70,4,-64,20,-52,-39,79,19,30,65,25,-71,-76,-1,62,-69,98,39,-25,-73,70,88,-17,-20,-75,55,34,57,81,-10,94,48,-35,5,-23,-44,40,-51,-61,-13,-86,63,71,-97,45,43,51,75,33,-34,92,47,-78,85,-26,97,-29,-92,-83,-59,74,96,68,77,16,-4,10,60,64,-21,-2,1,-91,86,46,76,-37,-19,-96,36,-98,29,-72,61,50,15,-95,-40,-43,-53,90,-15,-48,-27,-90,-54,72,-50,-49,-18,78,54,35,-38,99,44,-67,53,-12,-41,2,8,-14,-84,-56,-6,12,-45,42,-47,-46} ,
                new int[] { 93,28,84,83,-74,59,58,66,-66,-3,-79,-80,3,-22,-68,22,-85,-99,14,-88,9,32,17,-60,95,-93,82,21,91,-63,26,13,-16,-32,-11,-100,6,-62,49,-89,-31,87,-8,69,0,80,-7,-81,-65,-5,67,-30,31,52,-33,37,-57,-9,7,-28,-42,-82,38,-55,11,23,-36,27,56,89,73,-24,41,-58,-70,-87,20,-64,-52,4,18,-94,19,30,-76,-1,-71,62,-69,25,-73,-25,70,39,88,98,-20,-17,65,55,-75,79,34,-39,48,94,-23,5,-44,-35,40,-10,-61,-51,-13,81,63,-97,71,-86,57,45,24,-34,85,97,-26,-78,-83,-92,74,-59,96,-29,68,47,77,92,10,-4,16,60,33,-21,1,86,76,46,-37,-91,-2,64,75,51,-19,-96,43,-98,29,61,-72,50,36,-95,-40,-43,15,90,-15,-53,-77,-54,-90,-49,-50,72,-27,35,54,-38,78,-67,44,53,99,-41,-12,-18,8,2,-48,-56,-84,-14,-45,12,42,-6,-46,-47}
                )
        );
//        System.out.println(new MySolution1().buildTree( new int[]{1,2} , new int[] {2,1} ));

    }

}
