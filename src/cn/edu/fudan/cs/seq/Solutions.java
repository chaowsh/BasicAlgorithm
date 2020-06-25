package cn.edu.fudan.cs.seq;

public class Solutions {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;
        if (m == n) return head;
        if (m < 0 || n < 0) return head;
        int mixIndex = m >= n ? n : m;
        int maxIndex = m >= n ? m : n;
        ListNode node = head;
        //Reverse directly
        if (mixIndex == 1) {
            ListNode preNode = null;
            int i = 0;
            while (node != null) {
                i++;
                ListNode next = node.next;
                node.next = preNode;
                preNode = node;
                node = next;
                if (i == (maxIndex - mixIndex + 1)) {
                    break;
                }
            }
            head.next = node;
            return preNode;
        } else {
            //Find the (m-1) node
            ListNode targetPreNode;
            int i = 0;
            while (node != null) {
                i++;
                if (i == mixIndex - 1) break;
                node = node.next;
            }
            targetPreNode = node;
            ListNode currNode = targetPreNode.next;
            ListNode targetNode = currNode;
            ListNode preNode = null;
            int j = 0;
            while (currNode != null) {
                j++;
                ListNode nextNode = currNode.next;
                currNode.next = preNode;
                preNode = currNode;
                currNode = nextNode;
                if (j == (maxIndex - mixIndex + 1)) {
                    break;
                }
            }
            targetPreNode.next = preNode;
            targetNode.next = currNode;
            return head;
        }
    }

    public ListNode reverse(ListNode head) {
        if (head == null) return head;
        ListNode preNode = null;
        ListNode currNode = head;
        while (currNode != null) {
            ListNode next = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = next;
        }
        return preNode;
    }

    public ListNode rotateRight(ListNode head, int k) {
        return head;
    }

    public static void main(String []args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        //ListNode node2 = new ListNode(3);
        //ListNode node3 = new ListNode(4);
        //ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = null;
        //node2.next = node3;
        //node3.next = node4;
        //node4.next = null;

        Solutions s = new Solutions();
        /*
        ListNode reverseNode = s.reverse(head);
        System.out.println("Reserve all nodes: ");
        while (reverseNode != null) {
            System.out.print(reverseNode.val + "->");
            reverseNode = reverseNode.next;
        }*/

        System.out.println("Reserve nodes between 2 and 4: ");
        ListNode reverseNodeBet = s.reverseBetween(head, 1, 2);
        while (reverseNodeBet != null) {
            System.out.print(reverseNodeBet.val + "->");
            reverseNodeBet = reverseNodeBet.next;
        }
    }
}
