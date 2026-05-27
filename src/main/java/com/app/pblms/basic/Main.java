package com.app.pblms.basic;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

interface Vehicle {
    VehicleSize getSize();
    String getLicensePlate();
}

abstract class AbstractVehicle implements Vehicle {
    private final String licensePlate;

    protected AbstractVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

class Car extends AbstractVehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}

class ParkingSpot {
    private final int spotNumber;
    private final VehicleSize size;
    private Vehicle vehicle;

    ParkingSpot(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFit(Vehicle vehicle) {
        return vehicle.getSize().ordinal() <= size.ordinal();
    }

    public void occupy(Vehicle vehicle) {
        if (!isAvailable()) throw new RuntimeException("Spot is unavailable.");
        this.vehicle = vehicle;
    }

    public void vacate() {
        this.vehicle = null;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }
}

class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        parkingSpots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}

class Ticket {
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
    }

    public long getParkingDurationToMinutes() {
        LocalDateTime endTime = (exitTime == null) ? LocalDateTime.now() : exitTime;
        long minutes = Duration.between(entryTime, endTime).toMinutes();
        return Math.max(minutes, 1);
    }
}

enum PaymentStatus {
    SUCCESS, FAILED, PENDING
}

enum PaymentMethod {
    CASH
}

class Payment {
    private final String paymentId;
    private final PaymentMethod paymentMethod;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private PaymentStatus paymentStatus;

    public Payment(PaymentMethod paymentMethod, BigDecimal amount, String paymentId, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.createdAt = LocalDateTime.now();
    }
}

interface PaymentProcessor {
    Payment process(BigDecimal amount);
}

class CashPaymentProcessor implements PaymentProcessor {
    public Payment process(BigDecimal amount) {
        return new Payment(
                PaymentMethod.CASH,
                amount,
                UUID.randomUUID().toString(),
                PaymentStatus.SUCCESS
        );
    }
}

interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare);
}

class BaseStrategy implements FareStrategy {
    static final Map<VehicleSize, BigDecimal> RATE_MAP = Map.of(
            VehicleSize.SMALL, new BigDecimal("1"),
            VehicleSize.MEDIUM, new BigDecimal("2"),
            VehicleSize.LARGE, new BigDecimal("3")
    );

    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        long minutes = ticket.getParkingDurationToMinutes();
        VehicleSize size = ticket.getVehicle().getSize();
        BigDecimal rate = RATE_MAP.getOrDefault(size, BigDecimal.ONE);
        return currentFare.add(rate.multiply(BigDecimal.valueOf(minutes)));
    }
}

class PeakFareStrategy implements FareStrategy {
    static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        LocalDateTime entryTime = ticket.getEntryTime();
        if (isPeakHour(entryTime)) {
            return currentFare.multiply(PEAK_MULTIPLIER);
        }
        return currentFare;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 7 && hour < 9) || (hour >= 16 && hour <= 18);
    }
}

class FareCalculator {
    private final List<FareStrategy> fareStrategies;

    public FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal currentFare = BigDecimal.ZERO;
        for (FareStrategy currentFareStrategy : fareStrategies) {
            currentFare = currentFareStrategy.calculateFare(ticket, currentFare);
        }
        return currentFare;
    }
}

class ParkingLot {
    private final List<ParkingFloor> parkingFloors;
    private final Map<VehicleSize, Queue<ParkingSpot>> freeSpots;
    private final Map<String, ParkingSpot> vehicleParkingSpotMap;
    private final FareCalculator fareCalculator;

    private final Lock lock = new ReentrantLock();

    public ParkingLot(List<ParkingFloor> parkingFloors, FareCalculator fareCalculator) {
        this.parkingFloors = parkingFloors;
        this.fareCalculator = fareCalculator;
        this.freeSpots = new EnumMap<>(VehicleSize.class);
        this.vehicleParkingSpotMap = new ConcurrentHashMap<>();

        for (VehicleSize vehicleSize : VehicleSize.values()) {
            freeSpots.put(vehicleSize, new ConcurrentLinkedQueue<>());
        }

        initializeFreeSpots();
    }

    public Ticket park(Vehicle vehicle) {
        lock.lock(); // Acquire lock before compound actions
        try {
            String vehicleLicensePlate = vehicle.getLicensePlate();
            if (vehicleParkingSpotMap.containsKey(vehicleLicensePlate)) {
                throw new RuntimeException("Vehicle " + vehicleLicensePlate + " is already parked.");
            }

            ParkingSpot parkingSpot = allocateParkingSpot(vehicle);
            if (parkingSpot == null) {
                throw new RuntimeException("No available spot for vehicle " + vehicleLicensePlate);
            }

            parkingSpot.occupy(vehicle);
            vehicleParkingSpotMap.put(vehicleLicensePlate, parkingSpot);

            System.out.println("Parked: " + vehicleLicensePlate + " in Spot: " + parkingSpot.getSpotNumber());
            return new Ticket(vehicle, parkingSpot);
        } finally {
            lock.unlock(); // ALWAYS unlock in a finally block
        }
    }

    public BigDecimal unpark(Ticket ticket) {
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        String licensePlate = ticket.getVehicle().getLicensePlate();

        lock.lock();
        try {
            ticket.closeTicket();
            parkingSpot.vacate();

            freeSpots.get(parkingSpot.getSize()).offer(parkingSpot);
            vehicleParkingSpotMap.remove(licensePlate);
            System.out.println("Unparked: " + licensePlate + " from Spot: " + parkingSpot.getSpotNumber());
        } finally {
            lock.unlock();
        }

        // Calculate fare outside the lock to avoid blocking other parking operations
        return fareCalculator.calculateFare(ticket);
    }

    private void initializeFreeSpots() {
        for (ParkingFloor parkingFloor : parkingFloors) {
            for (ParkingSpot parkingSpot : parkingFloor.getParkingSpots()) {
                freeSpots.get(parkingSpot.getSize()).offer(parkingSpot);
            }
        }
    }

    private ParkingSpot allocateParkingSpot(Vehicle requestedVehicle) {
        VehicleSize requestedSize = requestedVehicle.getSize();

        for (VehicleSize currentSize : VehicleSize.values()) {
            if (currentSize.compareTo(requestedSize) < 0) continue;

            Queue<ParkingSpot> queue = freeSpots.get(currentSize);
            ParkingSpot spot = queue.poll();
            if (spot != null && spot.canFit(requestedVehicle)) {
                return spot;
            }
        }
        return null;
    }
}

class EntryGate {
    private final int gateId;
    private final ParkingLot parkingLot;

    public EntryGate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public Ticket enter(Vehicle vehicle) {
        return parkingLot.park(vehicle);
    }
}

class ExitGate {
    private int gateId;
    private final ParkingLot parkingLot;
    private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public ExitGate(int gateId, ParkingLot parkingLot, Map<PaymentMethod, PaymentProcessor> paymentProcessors) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.paymentProcessors = paymentProcessors;
    }

    public Payment exit(Ticket ticket, PaymentMethod paymentMethod) {
        BigDecimal amount = parkingLot.unpark(ticket);

        PaymentProcessor paymentProcessor = paymentProcessors.get(paymentMethod);
        if (paymentProcessor == null) throw new RuntimeException("Unsupported payment method");

        return paymentProcessor.process(amount);
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println("--- Initializing Parking Lot System ---");

        // 1. Create Parking Floors and Spots (Let's make a very small lot to force capacity issues)
        List<ParkingFloor> floors = new ArrayList<>();
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot(101, VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot(102, VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot(103, VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot(104, VehicleSize.LARGE));
        floors.add(floor1);

        // 2. Setup Fare Strategies
        List<FareStrategy> strategies = new ArrayList<>();
        strategies.add(new BaseStrategy());
        strategies.add(new PeakFareStrategy());
        FareCalculator fareCalculator = new FareCalculator(strategies);

        // 3. Initialize Parking Lot and Gates
        ParkingLot parkingLot = new ParkingLot(floors, fareCalculator);
        EntryGate entryGate = new EntryGate(1, parkingLot);

        System.out.println("--- Starting Concurrent Traffic Simulation ---");

        // 4. Simulate Concurrent Vehicles Arriving using a ThreadPool
        int numberOfCars = 6; // We have 6 cars, but only 4 spots!
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCars);

        for (int i = 1; i <= numberOfCars; i++) {
            final int carId = i;
            executorService.submit(() -> {
                String licensePlate = "KA-01-XY-" + String.format("%04d", carId);
                Vehicle car = new Car(licensePlate);
                Ticket ticket = null;

                try {
                    // Try to enter the gate
                    ticket = entryGate.enter(car);

                    // Simulate the car staying in the lot for a random time (1 to 3 seconds)
                    Thread.sleep((long) (Math.random() * 2000) + 1000);

                } catch (RuntimeException e) {
                    System.err.println("GATE ALERT for " + licensePlate + ": " + e.getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // Try to unpark if they successfully got a ticket
                    if (ticket != null) {
                        try {
                            BigDecimal fare = parkingLot.unpark(ticket);
                            System.out.println("Payment Due for " + licensePlate + ": $" + fare);
                        } catch (Exception e) {
                            System.err.println("Error unparking " + licensePlate + ": " + e.getMessage());
                        }
                    }
                }
            });
        }

        // 5. Shut down the executor and wait for all threads to finish
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- Simulation Complete ---");
    }

}