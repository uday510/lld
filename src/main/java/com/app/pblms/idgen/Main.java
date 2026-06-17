package com.app.pblms.idgen;


import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SnowflakeIdGenerator gen = new SnowflakeIdGenerator(42);

        // 1. Generate a few IDs, show they increase
        System.out.println("=== Sequential IDs (note they increase) ===");
        long prev = 0;
        for (int i = 0; i < 5; i++) {
            long id = gen.nextId();
            System.out.println("id=" + id
                    + "  ts=" + SnowflakeIdGenerator.extractTimestamp(id)
                    + "  machine=" + SnowflakeIdGenerator.extractMachineId(id)
                    + "  seq=" + SnowflakeIdGenerator.extractSequence(id)
                    + "  (increasing: " + (id > prev) + ")");
            prev = id;
        }

        // 2. Uniqueness under load: 1,000,000 IDs, assert no collisions
        System.out.println("\n=== Uniqueness test: 1,000,000 IDs ===");
        Set<Long> seen = new HashSet<>();
        int collisions = 0;
        long start = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            long id = gen.nextId();
            if (!seen.add(id)) collisions++;
        }
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Generated 1,000,000 IDs in " + elapsedMs + " ms");
        System.out.println("Collisions: " + collisions + " (must be 0)");
        System.out.println("Throughput: ~" + (1_000_000_000L / Math.max(elapsedMs, 1)) + " IDs/sec");

        // 3. Concurrent generation across threads — still unique
        System.out.println("\n=== Concurrent test: 8 threads x 100,000 IDs ===");
        Set<Long> concurrentSeen = java.util.concurrent.ConcurrentHashMap.newKeySet();
        java.util.concurrent.atomic.AtomicInteger dupes = new java.util.concurrent.atomic.AtomicInteger();
        Thread[] threads = new Thread[8];
        for (int t = 0; t < 8; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    long id = gen.nextId();
                    if (!concurrentSeen.add(id)) dupes.incrementAndGet();
                }
            });
            threads[t].start();
        }
        for (Thread th : threads) th.join();
        System.out.println("Total IDs: " + concurrentSeen.size() + " (expect 800,000)");
        System.out.println("Duplicates: " + dupes.get() + " (must be 0)");

        // 4. Two machines produce different IDs at the same instant
        System.out.println("\n=== Two machines, no collision ===");
        SnowflakeIdGenerator m1 = new SnowflakeIdGenerator(1);
        SnowflakeIdGenerator m2 = new SnowflakeIdGenerator(2);
        long id1 = m1.nextId();
        long id2 = m2.nextId();
        System.out.println("Machine 1 id: " + id1 + " (machine bits=" + SnowflakeIdGenerator.extractMachineId(id1) + ")");
        System.out.println("Machine 2 id: " + id2 + " (machine bits=" + SnowflakeIdGenerator.extractMachineId(id2) + ")");
        System.out.println("Different: " + (id1 != id2));
    }
}