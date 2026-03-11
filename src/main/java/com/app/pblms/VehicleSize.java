package com.app.pblms;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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

       if (!isAvailable()) {
           throw new RuntimeException();
       }

       this.vehicle = vehicle;
   }

   public void vacate() {
       this.vehicle = null;
   }

}

class ParkingFloor {

   private final int floorNumber;
   private final List<ParkingSpot> parkingSpots;

   public ParkingFloor(int floorNumber) {
       this.floorNumber = floorNumber;
       parkingSpots = new ArrayList<>();
   }

   public void addSpot(ParkingSpot spot) {
       parkingSpots.add(spot);
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

   public long getParkingDurationMinutes() {

       LocalDateTime endTime = (exitTime == null)
               ? LocalDateTime.now()
               : exitTime;

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
   private LocalDateTime createdAt;
   private final PaymentStatus paymentStatus;

   public Payment(PaymentMethod paymentMethod, BigDecimal amount, String paymentId, PaymentStatus paymentStatus) {
       this.paymentMethod = paymentMethod;
       this.amount = amount;
       this.paymentId = paymentId;
       this.paymentStatus = paymentStatus;
       createdAt = LocalDateTime.now();
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
       long mins = ticket.getParkingDurationMinutes();
       VehicleSize size = ticket.getVehicle().getSize();
       BigDecimal rate = RATE_MAP.getOrDefault(size, BigDecimal.ONE);
       return currentFare.add(rate.multiply(BigDecimal.valueOf(mins)));
   }
}

class PeakFareStrategy implements FareStrategy {
   static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");
   public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
       LocalDateTime entryTime = ticket.getEntryTime();
       if (!isPeakHr(entryTime)) return currentFare;
       return currentFare.multiply(PEAK_MULTIPLIER);
   }
   private boolean isPeakHr(LocalDateTime time) {
       int hr = time.getHour();
       return (hr >= 7 && hr <= 9) || (hr >= 16 && hr <= 18);
   }
}

class FareCalculator {
   private final List<FareStrategy> fareStrategies;
   public FareCalculator(List<FareStrategy> fareStrategies) {
       this.fareStrategies = fareStrategies;
   }

   public BigDecimal calculateFare(Ticket ticket) {
       BigDecimal fare = BigDecimal.ZERO;
       for (FareStrategy fareStrategy : fareStrategies) {
           fare = fareStrategy.calculateFare(ticket, fare);
       }
       return fare;
   }
}

class ParkingLot {

   private final List<ParkingFloor> parkingFloors;
   private final Map<VehicleSize, Queue<ParkingSpot>> freeSpots;
   private final Map<String, ParkingSpot> vehicleParkingSpotMap;
   private final FareCalculator fareCalculator;

   public ParkingLot (List<ParkingFloor> parkingFloors, FareCalculator fareCalculator) {
       this.parkingFloors = parkingFloors;
       this.fareCalculator = fareCalculator;
       this.freeSpots = new EnumMap<>(VehicleSize.class);
       this.vehicleParkingSpotMap = new ConcurrentHashMap<>();

       for (VehicleSize size : VehicleSize.values()) freeSpots.put(size, new ConcurrentLinkedQueue<>());

       initializeFreeSpots();
   }

   public Ticket park(Vehicle vehicle) {
       String plate = vehicle.getLicensePlate();
       ParkingSpot spot = allocateSpot(vehicle);

       if (spot == null) {
           throw new RuntimeException("Spot not available");
       }

       ParkingSpot existing = vehicleParkingSpotMap.putIfAbsent(plate, spot);
       if (existing != null) {
           freeSpots.get(spot.getSize()).offer(spot); // Return spot to pool
           throw new RuntimeException("Vehicle already parked");
       }

       try {
           spot.occupy(vehicle);
       } catch (Exception e) {
           vehicleParkingSpotMap.remove(plate);
           freeSpots.get(spot.getSize()).offer(spot);
           throw e;
       }

       return new Ticket(vehicle, spot);
   }

   public BigDecimal unpark(Ticket ticket) {
       ticket.closeTicket();
       ParkingSpot spot = ticket.getParkingSpot();
       spot.vacate();
       freeSpots.get(spot.getSize()).offer(spot);
       vehicleParkingSpotMap.remove(
               ticket.getVehicle().getLicensePlate()
       );
       return fareCalculator.calculateFare(ticket);
   }

   private ParkingSpot allocateSpot(Vehicle vehicle) {
       VehicleSize vehicleSize = vehicle.getSize();

       for (VehicleSize size : VehicleSize.values()) {
           if (size.compareTo(vehicleSize) < 0) continue;

           Queue<ParkingSpot> queue = freeSpots.get(size);
           ParkingSpot spot = queue.poll();
           if (spot != null && spot.canFit(vehicle)) return spot;
       }
       return null;
   }


   private void initializeFreeSpots() {
       for (ParkingFloor floor : parkingFloors) {
           for (ParkingSpot spot : floor.getParkingSpots()) {
               freeSpots.get(spot.getSize()).offer(spot);
           }
       }
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
   private final Map<PaymentMethod, PaymentProcessor> processors;

   public ExitGate(int gateId, ParkingLot parkingLot, Map<PaymentMethod, PaymentProcessor> processors) {
       this.gateId = gateId;
       this.parkingLot = parkingLot;
       this.processors = processors;
   }

   public Payment exit(Ticket ticket, PaymentMethod paymentMethod) {
       BigDecimal amount = parkingLot.unpark(ticket);

       PaymentProcessor paymentProcessor = processors.get(paymentMethod);

       if (paymentProcessor == null) throw new RuntimeException("Unsupported payment");

       return paymentProcessor.process(amount);
   }

}
