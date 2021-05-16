package Sort;

import LinkedList.ListNode;

public class Q147InsertionSortList {
    private static class MySolution{
        /**
         * 思路：多使用一个指针来存储下一个需要被排序的数组。
         */
        public ListNode insertionSortList(ListNode head) {
            if( head == null ) return null;
            if( head.next == null ) return head;

            ListNode fakeHead = new ListNode(Integer.MIN_VALUE,head);
            ListNode lastSorted = head;
            ListNode cur = head.next;
            ListNode cach = cur.next;
            while( cur != null ){
                cach = cur.next;
                if( cur.val > lastSorted.val ){
                    lastSorted.next = cur;
                    lastSorted = cur;
                    cur = cach;
                } else {
                    ListNode search = fakeHead;
                    while ( cur.val > search.next.val ) search = search.next;
                    cur.next = search.next;
                    search.next = cur;
                    cur = cach;
                }
            }
            lastSorted.next = null;
            return fakeHead.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.toListNode(new int[]{ 4,2,1,3 });
        System.out.println(new MySolution().insertionSortList(head));
    }
}
