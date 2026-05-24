package com.app.pblms;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cache;

    private final Node<K, V> head;
    private final Node<K, V> tail;

    private final ReentrantLock lock = new ReentrantLock();

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head  = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    public Optional<V> get(K key) {
        Objects.requireNonNull(key, "key");
        lock.lock();
        try {
            Node<K, V> node = cache.get(key);
            if (node == null) {
                return Optional.empty();
            }

            detach(node);
            appendToTail(node);
            return Optional.of(node.value);
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");

        lock.lock();
        try {
            Node<K, V> existing = cache.get(key);
            if (existing != null) {
                existing.value = value;
                detach(existing);
                appendToTail(existing);
                return;
            }

            if (cache.size() >= capacity) {
                Node<K, V> lru = head.next;
                detach(lru);
                cache.remove(lru.key);
            }

            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            appendToTail(newNode);

        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    private void detach(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void appendToTail(Node<K, V> node) {
        Node<K, V> prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    static void main() {

        LRUCache<String, String> cache = new LRUCache<>(3);

        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");

        System.out.println("get(A) = " + cache.get("A"));
        System.out.println("get(B) = " + cache.get("B"));

        System.out.println("size = " + cache.getSize());


        cache.put("D", "Date");
        System.out.println("get(C) = " + cache.get("C"));

        cache.put("A", "Apricot");
        cache.put("E", "Elderberry");

        System.out.println("get(B) = " + cache.get("B"));
        System.out.println("get(D) = " + cache.get("D"));
        System.out.println("get(A) = " + cache.get("A"));
        System.out.println("get(E) = " + cache.get("E"));

        System.out.println("get(Z) = " + cache.get("Z"));
    }
}