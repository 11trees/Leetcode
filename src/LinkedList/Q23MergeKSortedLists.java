package LinkedList;

public class Q23MergeKSortedLists {
    private static class MergeKLists{
        /*
            我的思路：
                每次找各链表的第一个，直到所有链表为空。
                时间复杂度确实有点高。
         */
        public static ListNode mergeKLists(ListNode[] lists) {
            ListNode head = new ListNode();
            ListNode p = head;
            while(!isAllNull(lists)){
                int index = findMin(lists);
                p.next = lists[index];
                //step
                lists[index] = lists[index].next;
                p = p.next;
            }
            return head.next;
        }

        private static boolean isAllNull(ListNode[] lists){
            for(int i = 0 ; i < lists.length ; i++){
                if(lists[i] != null) return false;
            }
            return true;
        }

        private static int findMin(ListNode[] lists){
            int index = findFirstNode(lists);
            int minVal = lists[index].val;
            int minIndex = index;
            for(int i = index + 1 ; i < lists.length ; i++){
                if(lists[i] != null && lists[i].val < minVal){
                    minVal = lists[i].val;
                    minIndex = i;
                }
            }
            return minIndex;
        }

        private static int findFirstNode(ListNode[] lists){
            int index = 0;
            while(lists[index] == null) index++;
            return index;
        }
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[] { ListNode.toListNode(new int[] {1,4,5}) , ListNode.toListNode(new int[] {1,3,4}) , ListNode.toListNode(new int[] {2,6}) };
        System.out.println(MergeKLists.mergeKLists(lists));
    }








}
