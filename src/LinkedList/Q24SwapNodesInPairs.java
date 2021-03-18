package LinkedList;

public class Q24SwapNodesInPairs {
    /*
    利用假的头节点来避免对第一个节点的特殊处理。
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        ListNode fakeHead = new ListNode(0,head);

        ListNode past = fakeHead;
        ListNode left = head;
        ListNode right = head.next;
        while(left != null && right != null){
            left.next = right.next;
            right.next = left;
            past.next = right;
            past = left;
            left = (left.next == null) ? null : left.next;
            if(left != null) right = (left.next == null) ? null : left.next;
        }
        return fakeHead.next;
    }

    //test code
    public static void main(String[] args) {
        ListNode head = ListNode.toListNode(new int[] {1,2,3,4,5});
        System.out.println(swapPairs(head));
    }
}
