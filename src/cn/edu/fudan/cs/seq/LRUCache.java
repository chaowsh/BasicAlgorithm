package cn.edu.fudan.cs.seq;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Integer capacity;
    private Integer count;
    private Map<Integer, DoubleNode> cache;
    private DoubleNode head, tail;

    public LRUCache(Integer cap) {
        this.capacity = cap;
        this.cache = new HashMap<>();
        head = new DoubleNode();
        tail = new DoubleNode();
        head.next = tail;
        tail.pre = head;
        count = 0;
    }


    public int get(int key) {
        DoubleNode node = cache.get(key);
        if (node == null) return  -1;
        //move the accessed node to head
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoubleNode node = cache.get(key);
        if (node == null) {
            DoubleNode newNode = new DoubleNode(key, value);
            cache.put(key, newNode);
            addNodeAfterHead(newNode);
            count ++;
            if (count > capacity) {
                DoubleNode tail = popTail();
                cache.remove(tail.key);
                count --;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(DoubleNode node) {
        removeNode(node);
        addNodeAfterHead(node);
    }

    //Always add the new node right after the head
    private void addNodeAfterHead(DoubleNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    //Remove an existing node from the double list
    private void removeNode(DoubleNode node) {
        DoubleNode pre = node.pre;
        DoubleNode next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    //Pop the current tail
    private DoubleNode popTail() {
        DoubleNode node = this.tail.pre;
        removeNode(node);
        return node;
    }

    public class DoubleNode {
        Integer key, value;
        DoubleNode pre, next;

        public DoubleNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
        public DoubleNode() {}
    }

    public static void main(String []args) {
        LRUCache cache = new LRUCache( 2 );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }

}
