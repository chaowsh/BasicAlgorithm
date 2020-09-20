package cn.edu.fudan.cs.seq;

public class Solution2 {
    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return  pre;
    }


    public static void main(String []args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        Solution2 s = new Solution2();
        ListNode reverseNode = s.reverse(head);
        System.out.println("Reserve all nodes: ");
        while (reverseNode != null) {
            System.out.print(reverseNode.val + "->");
            reverseNode = reverseNode.next;
        }
    }
}
