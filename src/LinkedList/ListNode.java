package LinkedList;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        if(this.next == null) return this.val + " ";
        return this.val + " " + this.next.toString();
    }

    public static ListNode toListNode(int[] arrays){
        ListNode head = new ListNode();
        ListNode p = head;
        for(int i = 0 ; i < arrays.length ; i++){
            ListNode node = new ListNode(arrays[i],null);
            p.next = node;
            //step
            p = p.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head = null;
    }
}
