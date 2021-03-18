package LinkedList;

public class Q2AddTwoNumbers {
    /*
    Attention:
        1. 使用三元运算符来减少代码行数 e1 ? e2 : e3
        2. 注意终止条件：最后进位的处理
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode();
        ListNode p = head;
        while( l1 != null || l2 != null){
            int val1 = ( l1 == null ) ? 0 : l1.val;
            int val2 = ( l2 == null ) ? 0 : l2.val;
            int sum = val1 + val2 + carry;
            carry = sum/10;
            int val = sum%10;
            ListNode now = new ListNode(val);
            p.next = now;
            p = now;
            if(l1 != null) l1 = (l1.next == null) ? null : l1.next;
            if(l2 != null) l2 = (l2.next == null) ? null : l2.next;
        }
        if(carry == 1) p.next = new ListNode(1,null);
        return head.next;
    }

    //test
    public static void main(String[] args) {
        ListNode l1 = ListNode.toListNode(new int[] {2,4,3});
        ListNode l2 = ListNode.toListNode(new int[] {2,4,3});
        System.out.println(addTwoNumbers(l1,l2));
    }
}
