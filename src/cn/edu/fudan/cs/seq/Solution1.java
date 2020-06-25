package cn.edu.fudan.cs.seq;


public class Solution1 {
    /***
     * 按k组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode next = end.next;
            end.next = null;
            ListNode start = pre.next;
            pre.next = resverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public ListNode resverse(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /***
     * 删除链表的第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromStart(ListNode head, int n) {
        if (head == null) return null;
        if (n <= 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy.next;
        int length = 0;
        ListNode preNode = dummy;
        while (node != null) {
            length ++;
            if (length == n) {
                preNode.next = node.next;
                break;
            } else {
                preNode = preNode.next;
                node = node.next;
            }
        }
        return dummy.next;
    }

    /***
     * 删除链表的倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (n <= 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy.next;
        ListNode second = dummy;
        ListNode thrid = dummy;
        int length = 0;
        while (node != null) {
            length ++;
            if (length < n) {
                node = node.next;
                continue;
            }
            if (length >= n + 1) {
                thrid = thrid.next;
            }
            second = second.next;
            node = node.next;
            if (node == null) {
                thrid.next = second.next;
            }
        }
        return dummy.next;
    }

    /***
     * 删除链表的倒数第N个节点
     * 定义两个指针,之间相隔n+1个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        if (n <= 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i=1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    public static void main(String []args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution1 s = new Solution1();
        ListNode head = s.reverseKGroup(node1, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
