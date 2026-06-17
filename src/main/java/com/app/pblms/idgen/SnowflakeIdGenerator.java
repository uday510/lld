package com.app.pblms.idgen;

public class SnowflakeIdGenerator {

    // ===== Bit allocation =====
    private static final long UNUSED_BITS    = 1;   // sign bit
    private static final long TIMESTAMP_BITS = 41;
    private static final long MACHINE_BITS   = 10;
    private static final long SEQUENCE_BITS  = 12;

    // ===== Max values (derived from bit widths) =====
    // (1L << n) - 1  gives an n-bit mask of all 1s, i.e., the max value
    private static final long MAX_MACHINE_ID = (1L << MACHINE_BITS) - 1;   // 1023
    private static final long MAX_SEQUENCE   = (1L << SEQUENCE_BITS) - 1;  // 4095

    // ===== Bit shifts (where each field starts) =====
    private static final long MACHINE_SHIFT   = SEQUENCE_BITS;                    // 12
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_BITS;     // 22

    // ===== Custom epoch: Jan 1, 2024 00:00:00 UTC in milliseconds =====
    private static final long CUSTOM_EPOCH = 1704067200000L;

    private final long machineId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public SnowflakeIdGenerator(long machineId) {
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException(
                    "Machine ID must be between 0 and " + MAX_MACHINE_ID + ", got " + machineId);
        }
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currentTimestamp = currentTime();

        // ----- Clock moved backwards: refuse to generate -----
        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException(
                    "Clock moved backwards. Refusing to generate id for "
                            + (lastTimestamp - currentTimestamp) + " ms");
        }

        if (currentTimestamp == lastTimestamp) {
            // ----- Same millisecond: increment sequence -----
            sequence = (sequence + 1) & MAX_SEQUENCE;   // wraps to 0 on overflow
            if (sequence == 0) {
                // Sequence exhausted this millisecond — wait for the next tick
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // ----- New millisecond: reset sequence -----
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // ----- Assemble the 64-bit ID by shifting each field into place -----
        return ((currentTimestamp - CUSTOM_EPOCH) << TIMESTAMP_SHIFT)
                | (machineId << MACHINE_SHIFT)
                | sequence;
    }

    private long waitNextMillis(long lastTs) {
        long ts = currentTime();
        while (ts <= lastTs) {
            ts = currentTime();   // spin until clock advances
        }
        return ts;
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }

    // ===== Decode helpers (useful for debugging and demonstrating sortability) =====
    public static long extractTimestamp(long id) {
        return (id >> TIMESTAMP_SHIFT) + CUSTOM_EPOCH;
    }

    public static long extractMachineId(long id) {
        return (id >> MACHINE_SHIFT) & MAX_MACHINE_ID;
    }

    public static long extractSequence(long id) {
        return id & MAX_SEQUENCE;
    }
}