package LinkedList;

public class Q25ReverseNodesinKGroup {
    //个人思路
    private static class ReverseNodes{
        /*
            自己的思路：
                1. 探索下一段链表是否够数，不够则返回
                2. 够数则切断并反转链表
                3. 重新链接链表
                4. 循环
         */
        public static ListNode reverseNodes(ListNode head , int k){
            ListNode fakeHead = new ListNode(0,head);
            ListNode p = fakeHead;
            ListNode prev = fakeHead;
            while (p != null){
                ListNode begin = prev.next;
                for(int i = 0 ; i < k ; i++){
                    p = p.next;
                    if (p == null) return fakeHead.next;
                }
                ListNode next = p.next;
                p.next = null;
                prev.next = linkList(reverse(begin) , next);
                //step
                prev = begin;
                p = begin;
            }
            return fakeHead.next;
        }

        private static ListNode reverse(ListNode head){
            if(head == null) return null;
            ListNode fakeHead = new ListNode(0,head);
            ListNode prev = fakeHead;
            if (head.next == null) return head;
            ListNode cur = head;
            ListNode next = cur.next;
            while (cur != null){
                cur.next = prev;
                //step
                prev = cur;
                cur = next;
                next = (next == null) ? null : next.next;
            }
            head.next = null;
            return prev;
        }

        private static ListNode linkList(ListNode l1 , ListNode l2){
            ListNode p = l1;
            while (p.next != null){
                p = p.next;
            }
            p.next = l2;
            return l1;
        }
    }

    //test code
    public static void main(String[] args) {
        ListNode head = ListNode.toListNode(new int[] {1,2,3,4,5});
        System.out.println(ReverseNodes.reverseNodes(head,2));
    }
}
