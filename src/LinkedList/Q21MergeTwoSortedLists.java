package LinkedList;

import java.util.List;

public class Q21MergeTwoSortedLists {
    //迭代法
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*
        简单的比较两个头节点
        */
        ListNode head = new ListNode();
        ListNode p = head;
        while(l1 != null || l2 != null){
            if(l1 == null) {
                p.next = l2;
                //step
                l2 = null;
                continue;
            }
            if(l2 == null){
                p.next = l1;
                //step
                l1 = null;
                continue;
            }
            if(l1.val <= l2.val){
                p.next = l1;
                //step
                p = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                //step
                p = l2;
                l2 = l2.next;
            }
        }
        return head.next;
    }

    //分治策略
    public static ListNode mergeTwoListsRecur(ListNode l1 , ListNode l2){
        /*
            递归重点是将问题拆分成结构相同的子问题。(假设l1.val < l2.val)
            1. Divide：
                观察合并l1 l2 -> 返回一条合并链
                取l1后 需合并l1.next l2 -> 返回剩余节点的合并链
                可以看到两个问题结构相同。
            2. Conquer：
                直到其中一个 == null位置，例如l1 == null 则返回 l2 即可
            3. Combine:
                子问题返回的链应该接在前一个问题后，故采用 l1.next = 子问题的形式。
         */
        //Base case
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        //Divide
        if(l1.val < l2.val){
            //Combine
            l1.next = mergeTwoListsRecur(l1.next , l2);
            return l1;
        } else {
            //Combine
            l2.next = mergeTwoListsRecur(l1 , l2.next);
            return l2;
        }


    }

    //test code
    public static void main(String[] args) {
        ListNode l1 = ListNode.toListNode(new int[] {1,2,4});
        ListNode l2 = ListNode.toListNode(new int[] {1,3,4});
        System.out.println(mergeTwoLists(l1,l2));

        ListNode l3 = ListNode.toListNode(new int[] {1,2,4});
        ListNode l4 = ListNode.toListNode(new int[] {1,3,4});
        System.out.println(mergeTwoListsRecur(l3,l4));
    }
}
