package Tree;

import org.junit.Assert;
import org.junit.Test;

public class Q124BinaryTreeMaximumPathSum {
    private static class MySolution{
        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxContribution(root);
            return maxSum;
        }

        public int maxContribution( TreeNode root ){
            int ret = 0;

            int leftContri = root.left == null ? 0 : Math.max( 0, maxContribution(root.left) );
            int rightContri = root.right == null ? 0 : Math.max( 0, maxContribution(root.right) );
            ret = Math.max(leftContri, rightContri);

            int nowSum = leftContri + rightContri + root.val;
            if( nowSum > maxSum ) maxSum = nowSum;

            return ret + root.val;
        }
    }

    @Test
    public void testSolution(){
        TreeNode root1 = TreeNode.NodeFromList( TreeNode.ListFromString( "[1,2,3]" ) );
        TreeNode root2 = TreeNode.NodeFromList( TreeNode.ListFromString( "[-10,9,20,null,null,15,7]" ) );

        int sum1 = new MySolution().maxPathSum( root1 );
        int sum2 = new MySolution().maxPathSum( root2 );

        Assert.assertEquals(sum1 , 6);
        Assert.assertEquals(sum2 , 42);

    }
}
