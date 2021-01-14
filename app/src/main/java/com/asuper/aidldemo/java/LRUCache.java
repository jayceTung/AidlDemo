package com.asuper.aidldemo.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperM1n
 * @brief description
 * @date 2021-01-12
 */
public class LRUCache {

    int capacity;
    int size;
    LRUNode head;
    LRUNode tail;
    Map<Integer, LRUNode> cache;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new LRUNode(-1, -1);
        tail = new LRUNode(-1, -1);
        cache = new HashMap<>(capacity);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        LRUNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LRUNode node = cache.get(key);
        if (node == null) {
            node = new LRUNode(key, value);
            if (removeEldestEntry()) {
                removeTail();
            } else {
                size ++;
            }
        } else {
            node.value = value;
            removeNode(node);
        }
        addHead(node);
    }

    public boolean removeEldestEntry() {
        return size >= capacity;
    }

    public void removeNode(LRUNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addHead(LRUNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        cache.put(node.key, node);
    }

    public void removeTail() {
        LRUNode node = tail.pre;
        removeNode(node);
        cache.remove(node.key);
    }



    class LRUNode {
        int key;
        int value;
        LRUNode next;
        LRUNode pre;

        public LRUNode(int key, int value) {
            this(key, value, null, null);
        }

        public LRUNode(int key, int value, LRUNode next, LRUNode pre) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
    }
}
