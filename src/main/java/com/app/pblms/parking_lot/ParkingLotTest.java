package com.app.pblms.parking_lot;

import com.app.pblms.parking_lot.fare.BaseFareStrategy;
import com.app.pblms.parking_lot.fare.FareCalculator;
import com.app.pblms.parking_lot.fare.PeakHourFareStrategy;
import com.app.pblms.parking_lot.floor.ParkingFloor;
import com.app.pblms.parking_lot.gate.EntryGate;
import com.app.pblms.parking_lot.gate.ExitGate;
import com.app.pblms.parking_lot.lot.ParkingLot;
import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.ticket.Ticket;
import com.app.pblms.parking_lot.vehicle.Car;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import com.app.pblms.parking_lot.payment.PaymentMethod;
import com.app.pblms.parking_lot.vehicle.Motorcycle;
import com.app.pblms.parking_lot.vehicle.Truck;

import java.util.List;

public class ParkingLotTest {

    static void main(String[] args) {
        System.out.println("=== INITIALIZING PARKING LOT SYSTEM ===");

        // 1. Setup Floors and Spots (Capacity: 2 Small, 2 Medium, 2 Large)
        ParkingFloor parkingFloor1 = new ParkingFloor(1);
        parkingFloor1.addParkingSpots(List.of(
                new ParkingSpot(100, VehicleSize.SMALL),
                new ParkingSpot(101, VehicleSize.MEDIUM),
                new ParkingSpot(102, VehicleSize.LARGE)
        ));

        ParkingFloor parkingFloor2 = new ParkingFloor(2);
        parkingFloor2.addParkingSpots(List.of(
                new ParkingSpot(200, VehicleSize.SMALL),
                new ParkingSpot(201, VehicleSize.MEDIUM),
                new ParkingSpot(202, VehicleSize.LARGE)
        ));

        // 2. Setup Strategies and Calculator
        FareCalculator fareCalculator = new FareCalculator(
                List.of(new BaseFareStrategy(), new PeakHourFareStrategy()
        ));

        // 3. Initialize Parking Lot
        ParkingLot parkingLot = new ParkingLot(List.of(parkingFloor1, parkingFloor2), fareCalculator);

        // 4. Setup Gates
        EntryGate entryGate1 = new EntryGate(1, parkingLot);
        EntryGate entryGate2 = new EntryGate(2, parkingLot);
        
        ExitGate exitGate1 = new ExitGate(1, parkingLot);
        ExitGate exitGate2 = new ExitGate(2, parkingLot);

        // 5. Create Vehicles
        Vehicle motorcycle1 = new Motorcycle("MOTO-111");
        Vehicle motorcycle2 = new Motorcycle("MOTO-222");
        Vehicle motorcycle3 = new Motorcycle("MOTO-333"); // Used to test overflow
        Vehicle car1 = new Car("CAR-111");
        Vehicle car2 = new Car("CAR-222");
        Vehicle truck1 = new Truck("TRK-111");

        System.out.println("Initialization Complete.\n");

        // ==========================================
        // TEST CASE 1: Standard Parking & Unparking
        // ==========================================
        System.out.println("--- TEST CASE 1: Standard Flow ---");
        try {
            Ticket t1 = entryGate1.enter(motorcycle1);
            System.out.println("Parked Motorcycle in spot: " + t1.getParkingSpot().getSpotNumber());

            Ticket t2 = entryGate2.enter(car1);
            System.out.println("Parked Car in spot: " + t2.getParkingSpot().getSpotNumber());

            // Unpark and pay
            Payment p1 = exitGate1.exit(t1, PaymentMethod.CASH);
            System.out.println("Motorcycle exited. Paid: $" + p1.getAmount() + " via " + p1.getPaymentMethod());

            Payment p2 = exitGate2.exit(t2, PaymentMethod.CREDIT_CARD);
            System.out.println("Car exited. Paid: $" + p2.getAmount() + " via " + p2.getPaymentMethod());
            
            System.out.println("Test Case 1 Passed.\n");
        } catch (Exception e) {
            System.out.println("Test Case 1 Failed: " + e.getMessage());
        }

        // ==========================================
        // TEST CASE 2: Duplicate Vehicle Prevention
        // ==========================================
        System.out.println("--- TEST CASE 2: Duplicate Vehicle ---");
        try {
            Ticket t3 = entryGate1.enter(truck1);
            System.out.println("Parked Truck in spot: " + t3.getParkingSpot().getSpotNumber());
            
            // Try to park the exact same truck again
            System.out.println("Attempting to park the same Truck again...");
            entryGate2.enter(truck1); 
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Test Case 2 Passed.\n");
        }

        // ==========================================
        // TEST CASE 3: Vehicle Upgrades Spot (Small in Medium Spot)
        // ==========================================
        System.out.println("--- TEST CASE 3: Spot Upgrade ---");
        try {
            // Capacity is 2 small spots. Let's fill them.
            entryGate1.enter(motorcycle1); // Fills Small Spot 1 (Floor 1)
            entryGate1.enter(motorcycle2); // Fills Small Spot 2 (Floor 2)
            
            // Try to park a 3rd motorcycle. 
            // Based on your logic: vehicleSize.ordinal() <= parkingSpotSize.ordinal()
            // It should grab a MEDIUM spot!
            Ticket overflowTicket = entryGate2.enter(motorcycle3);
            System.out.println("3rd Motorcycle parked in spot: " + overflowTicket.getParkingSpot().getSpotNumber() 
                    + " (Size: " + overflowTicket.getParkingSpot().getVehicleSize() + ")");
            System.out.println("Test Case 3 Passed.\n");
        } catch (Exception e) {
            System.out.println("Test Case 3 Failed: " + e.getMessage());
        }

        // ==========================================
        // TEST CASE 4: Lot Full / Unavailability
        // ==========================================
        System.out.println("--- TEST CASE 4: Lot Full / No Spots ---");
        try {
            // Currently Parked: Truck1(Large), Moto1(Small), Moto2(Small), Moto3(Medium)
            // Remaining: 1 Medium, 1 Large.
            
            entryGate1.enter(car2); // Takes the last Medium spot
            
            Vehicle truck2 = new Truck("TRK-222");
            entryGate2.enter(truck2); // Takes the last Large spot
            
            // Lot is effectively full for Cars and Trucks now (Only a small vehicle could technically steal a large spot, but let's try a Truck)
            System.out.println("Attempting to park a 3rd Truck when no Large spots are left...");
            Vehicle truck3 = new Truck("TRK-333");
            entryGate1.enter(truck3); 
            
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Test Case 4 Passed.\n");
        }
        
        System.out.println("=== TESTING COMPLETE ===");
    }
}

/**

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

enum PaymentStatus {
    PENDING, FAILED, SUCCESS
}

enum PaymentMethod {
    CREDIT_CARD, CASH
}


interface Vehicle {
    String getLicensePlate();
     VehicleSize getSize();
}
abstract class AbstractVehicle implements Vehicle {
    private final String licensePlate;

    AbstractVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

}

class MotorCycle extends AbstractVehicle {
    MotorCycle(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}

class Car extends AbstractVehicle {
    Car(String licensePlate) {
        super(licensePlate);
    }
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}

class Truck extends AbstractVehicle {
    Truck(String licensePlate) {
        super(licensePlate);
    }
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}

class ParkingSpot {
    private final int id;
    private final VehicleSize size;

    private Vehicle vehicle;

    ParkingSpot(VehicleSize size, int id) {
        this.size = size;
        this.id = id;
    }

    void occupy(Vehicle vehicle) {
        if (this.vehicle != null)
            throw new RuntimeException("Spot is already occupied");

        this.vehicle = vehicle;
    }

    void vacate() {
        this.vehicle = null;
    }

    VehicleSize getSize() {
        return size;
    }

    boolean isFree() {
        return vehicle == null;
    }

    boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getSize().ordinal() <= size.ordinal();
    }

}

class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    ParkingFloor (int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new CopyOnWriteArrayList<>();
    }

    List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    public void addParkingSpots(List<ParkingSpot> parkingSpots) {
        parkingSpots.forEach(this::addParkingSpot);
    }
}

class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final Instant entryTime;

    private Instant exitTime;

    Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryTime = Instant.now();
        this.parkingSpot = parkingSpot;
    }

    void closeTicket() {
        this.exitTime = Instant.now();
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    long getParkingDurationInMins() {
        Instant endTime = (exitTime == null) ? Instant.now() : exitTime;

        return Math.max(1, Duration.between(entryTime, endTime).toMinutes());
    }

    Instant getEntryTime() {
        return entryTime;
    }
}

interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal runningFare);
}

class BaseFareStrategy implements FareStrategy {

    static final Map<VehicleSize, BigDecimal> RATE_MAP = Map.of(
      VehicleSize.SMALL, new BigDecimal("1.0"),
      VehicleSize.MEDIUM, new BigDecimal("2.0"),
      VehicleSize.LARGE, new BigDecimal("3.0")
    );

    public BigDecimal calculateFare(Ticket ticket, BigDecimal runningFare) {

        BigDecimal rate = RATE_MAP.getOrDefault(ticket.getVehicle().getSize(), BigDecimal.ONE);

        long duration = Math.min(1, ticket.getParkingDurationInMins());

        return runningFare.add(rate.multiply(BigDecimal.valueOf(duration)));
    }
}

class PeakHourFareStrategy implements FareStrategy {
    static final BigDecimal peakMultiplier = new BigDecimal("1.5");

    public BigDecimal calculateFare(Ticket ticket, BigDecimal runningFare) {
        if (isPeakHr(ticket.getEntryTime())) {
           return runningFare.multiply(peakMultiplier);
        }

        return runningFare;
    }

    private boolean isPeakHr(Instant time) {

        ZoneId zone = ZoneId.systemDefault();

        long hour =  time.atZone(zone).getHour();

        boolean isMorning = (hour >= 8 && hour < 10);
        boolean isEvening = (hour >= 17 && hour < 19);

        return isMorning || isEvening;
    }
}

class FareCalculator {
    private final List<FareStrategy> fareStrategies;

    FareCalculator (List<FareStrategy> fareStrategies) {
        this.fareStrategies = List.copyOf(fareStrategies);
    }

    BigDecimal calculateFare(Ticket ticket) {
        BigDecimal currentFare = BigDecimal.ZERO;

        for (FareStrategy fareStrategy : fareStrategies) {
            currentFare = fareStrategy.calculateFare(ticket, currentFare);
        }

        return currentFare;
    }
}

class Payment {

    private final String paymentId;
    private final PaymentMethod paymentMethod;
    private final PaymentStatus paymentStatus;
    private final BigDecimal amount;
    private final Instant createdAt;

    Payment (String paymentId, PaymentMethod paymentMethod, PaymentStatus paymentStatus, BigDecimal amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.createdAt = Instant.now();
    }
}

interface PaymentProcessor {
    Payment pay(BigDecimal amount);
}

class CashPaymentProcessor implements PaymentProcessor {

    public Payment pay(BigDecimal amount) {
        return new Payment(
                UUID.randomUUID().toString(),
                PaymentMethod.CASH,
                PaymentStatus.SUCCESS,
                amount
        );
    }

}

class CreditCardPaymentProcessor implements PaymentProcessor {

    public Payment pay(BigDecimal amount) {
        return new Payment(
                UUID.randomUUID().toString(),
                PaymentMethod.CREDIT_CARD,
                PaymentStatus.SUCCESS,
                amount
        );
    }

}

class PaymentService {

    private final Map<PaymentMethod, PaymentProcessor> processors;

    PaymentService() {
        processors = new EnumMap<>(PaymentMethod.class);
        processors.put(PaymentMethod.CASH, new CashPaymentProcessor());
        processors.put(PaymentMethod.CREDIT_CARD, new CreditCardPaymentProcessor());
    }

    Payment pay(PaymentMethod paymentMethod, BigDecimal amount) {
        PaymentProcessor processor = processors.get(paymentMethod);
        if (processor == null)
                throw new RuntimeException("Payment Method not allowed.");

        return processor.pay(amount);
    }
}

class ParkingLot {

    private final List<ParkingFloor> parkingFloors;
    private final PaymentService paymentService;
    private final FareCalculator fareCalculator;
    private final Map<VehicleSize, ConcurrentLinkedQueue<ParkingSpot>> freeSpots;
    private final Map<String, ParkingSpot> vehicleParkingStore;
    private final VehicleSize[] vehicleSizes;

    ParkingLot(
        List<ParkingFloor> parkingFloors,
        FareCalculator fareCalculator
    ) {
        this.parkingFloors = List.copyOf(parkingFloors);
        this.freeSpots = new EnumMap<>(VehicleSize.class);
        this.fareCalculator = fareCalculator;
        this.paymentService = new PaymentService();
        this.vehicleParkingStore = new ConcurrentHashMap<>();
        this.vehicleSizes = VehicleSize.values();

        for (VehicleSize vehicleSize : vehicleSizes) {
            freeSpots.put(vehicleSize, new ConcurrentLinkedQueue<>());
        }

        initializeFreeSpots();
    }


    public Ticket park(Vehicle vehicle) {
        String plate = vehicle.getLicensePlate();

        ParkingSpot parkingSpot = getParkingSpot(vehicle);

        if (parkingSpot == null) {
            throw new IllegalStateException("Parking spot unavailable for size " + vehicle.getSize());
        }

        ParkingSpot existing = vehicleParkingStore.putIfAbsent(plate, parkingSpot);
        if (existing != null) {
            releaseSpot(parkingSpot);
            throw new IllegalStateException("Vehicle " + plate + " is already parked.");
        }

        try {
            parkingSpot.occupy(vehicle);
        } catch (Exception e) {
            vehicleParkingStore.remove(plate);
            releaseSpot(parkingSpot);
            throw e;
        }

        return new Ticket(vehicle, parkingSpot);
    }

    public Payment unpark(Ticket ticket, PaymentMethod paymentMethod) {
        String plate = ticket.getVehicle().getLicensePlate();

        ParkingSpot parkingSpot = vehicleParkingStore.remove(plate);
        if (parkingSpot == null) {
            throw new IllegalStateException("Vehicle not yet parked");
        }

        ticket.closeTicket();
        BigDecimal fare = fareCalculator.calculateFare(ticket);

        Payment payment;
        try {
            payment = paymentService.pay(paymentMethod, fare);
        } catch (Exception e) {
            vehicleParkingStore.put(plate, parkingSpot);

            throw new IllegalStateException("Payment failed. Vehicle cannot exit.");
        }

        parkingSpot.vacate();
        releaseSpot(parkingSpot);

        return payment;
    }

    private void releaseSpot(ParkingSpot parkingSpot) {
        freeSpots.get(parkingSpot.getSize()).offer(parkingSpot);
    }

    private ParkingSpot getParkingSpot(Vehicle vehicle) {
        VehicleSize reqVehicleSize = vehicle.getSize();

        for (VehicleSize curVehicleSize : vehicleSizes) {

            if (curVehicleSize.compareTo(reqVehicleSize) < 0) continue;;

            ConcurrentLinkedQueue<ParkingSpot> queue = freeSpots.get(curVehicleSize);

            if (queue == null || queue.isEmpty()) continue;

            ParkingSpot parkingSpot = queue.poll();

            if (parkingSpot != null) {
                return parkingSpot;
            }
        }

        return null;
    }

    void initializeFreeSpots() {

        for (ParkingFloor parkingFloor : parkingFloors) {
            for (ParkingSpot parkingSpot : parkingFloor.getParkingSpots()) {
                freeSpots.get(parkingSpot.getSize()).offer(parkingSpot);
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
    private final int gateId;
    private final ParkingLot parkingLot;

    public ExitGate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public Payment exit(Ticket ticket, PaymentMethod paymentMethod) {
        return parkingLot.unpark(ticket, paymentMethod);
    }
}

void main() {

    ParkingFloor parkingFloor1 = new ParkingFloor(1);

    parkingFloor1.addParkingSpots(List.of(
            new ParkingSpot(VehicleSize.SMALL, 100),
            new ParkingSpot(VehicleSize.MEDIUM, 200)
    ));

    List<ParkingFloor> parkingFloors = List.of(parkingFloor1);

    Vehicle car = new Car("123-456");
    FareCalculator fareCalculator = new FareCalculator(new ArrayList<>(List.of(new BaseFareStrategy(), new PeakHourFareStrategy())));

    ParkingLot parkingLot = new ParkingLot(
            parkingFloors,
            fareCalculator
    );

    EntryGate entryGate1 = new EntryGate(100, parkingLot);
    ExitGate exitGate1 = new ExitGate(200, parkingLot);

    Ticket ticket = entryGate1.enter(car);
    Payment payment = exitGate1.exit(ticket, PaymentMethod.CASH);

}

 */