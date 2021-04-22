package Tree;

import java.util.*;

public class Q297SerializeAndDeserializeBinaryTree {
    public static class MyCodec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            Deque<TreeNode> queue = new ArrayDeque<>();
            if( root == null ) return new String("[]");

            queue.addFirst(root);
            Map<TreeNode , Integer> hashMap = new HashMap<>();
            hashMap.put( root , 1 );

            TreeNode lastNode = root;
            while( !queue.isEmpty() ){
                TreeNode node = queue.removeLast();
                lastNode = node;

                int index = hashMap.get( node );

                if( node.left != null ) {
                    queue.addFirst( node.left );
                    hashMap.put( node.left , index * 2 );
                }
                if( node.right != null ) {
                    queue.addFirst( node.right );
                    hashMap.put( node.right , index * 2 + 1);
                }
            }

            long maxIndex = hashMap.get(lastNode);
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0 ; i < maxIndex + 1 ; i++){
                list.add(null);
            }
            for( TreeNode node : hashMap.keySet() ){
                int index = hashMap.get(node);
                list.set( index , node.val);
            }

            list.remove(0);
            String ret = list.toString();
            return ret.replaceAll("\\s", "");
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return NodeFromList( ListFromString( data ) );
        }

        public TreeNode NodeFromList (ArrayList<Integer> vals){
            /*-----从数组构建一般二叉树-----*/
            ArrayList<TreeNode> treeNodes = new ArrayList<>();
            /*
             * 添加一个头节点 和 数组的头节点
             * 因为基1的话，数组下标和子树关系十分方便表示。
             * 例如自身编号为 i 时
             * if ( i % 2 == 0 ) i 为 i/2 的左子树。
             * if ( i % 2 == 1 ) i 为 i/2 的右子树。
             * */
            if( vals == null ) return null;

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

        public ArrayList<Integer> ListFromString(String str){
            ArrayList<Integer> ret = new ArrayList<>();
            if( str.equals("[]") ) return null;

            //去掉头尾的[]符号
            String string = str.substring(1 , str.length() - 1);
            String[] strings = string.split(",");
            for(int i = 0 ; i < strings.length ; i++){
                if( strings[i].equals("null") ) ret.add(null);
                else ret.add(Integer.parseInt(strings[i]));
            }
            return ret;
        }
    }
}
