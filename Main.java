import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

interface Vehicle {
    String getLicensePlate();
    VehicleSize getSize();
}

class Motorcycle implements Vehicle {
    private final String licensePlate;

    public Motorcycle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}

class Car implements Vehicle {
    private final String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}

class Truck implements Vehicle {
    private final String licensePlate;

    public Truck(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}

interface ParkingSpot {
    boolean isAvailable();
    void occupy(Vehicle vehicle);
    void vacate();
    int getSpotNumber();
    VehicleSize getSize();
}

class ParkingSpotImpl implements ParkingSpot {
    private final int spotNumber;
    private final VehicleSize size;
    private boolean available;
    private Vehicle vehicle;

    public ParkingSpotImpl(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void occupy(Vehicle vehicle) {
        if (!available) {
            throw new IllegalStateException("Spot Unavailable.");
        }

        this.vehicle = vehicle;
        available = false;
    }

    public void vacate() {
        if (available) {
            throw new IllegalStateException("Spot Already available.");
        }

        this.vehicle = null;
        available = true;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }

    public VehicleSize getSize() {
        return size;
    }
}

class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Payment payment;

    public Ticket(ParkingSpot parkingSpot, Vehicle vehicle, LocalDateTime entryTime) {
        this.ticketId = generateTicket();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    private String generateTicket() {
        return "TICKET"+ System.currentTimeMillis();
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public BigDecimal calculateParkingDuration() {
        long mins = Duration.between(entryTime, Objects.requireNonNullElse(exitTime, LocalDateTime.now())).toMinutes();

        return BigDecimal.valueOf(mins);
    }
}

class ParkingFloor {
    private final int floorNumber;
    private final Map<VehicleSize, List<ParkingSpot>> vehicleSizeListMapeh;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.vehicleSizeListMapeh = new EnumMap<>(VehicleSize.class);

        for (VehicleSize size : VehicleSize.values()) {
            vehicleSizeListMapeh.put(size, new ArrayList<>());
        }
    }

    public void addSpot(ParkingSpot spot) {
        vehicleSizeListMapeh.get(spot.getSize()).add(spot);
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() < vehicleSize.ordinal()) continue;
            for (ParkingSpot parkingSpot : vehicleSizeListMapeh.get(size)) {
                if (parkingSpot.isAvailable()) {
                    return parkingSpot;
                }
            }
        }

        return null;
    }

}

class ParkingManager {
    private final List<ParkingFloor> parkingFloors;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpotMap;

    ParkingManager(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
        this.vehicleParkingSpotMap = new HashMap<>();
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = this.findSpotForVehicle(vehicle);
        if (parkingSpot == null) {
            throw new RuntimeException("parking-spot unavailable.");
        }

        parkingSpot.occupy(vehicle);
        vehicleParkingSpotMap.put(vehicle, parkingSpot);
        return parkingSpot;
    }

    private ParkingSpot findSpotForVehicle(Vehicle vehicle) {

        for (ParkingFloor floor : parkingFloors) {
            ParkingSpot parkingSpot = floor.findSpotForVehicle(vehicle);
            if (parkingSpot.isAvailable()) {
                return parkingSpot;
            }
        }
        return null;
    }

    public void unpark(Vehicle vehicle) {
        ParkingSpot spot = vehicleParkingSpotMap.get(vehicle);
        if (spot == null) {
            throw new RuntimeException("Spot is already free.");
        }

        spot.vacate();
    }
}

enum PaymentMethod {
    CASH
}

enum PaymentStatus {
    PENDING, FAILED, SUCCESS
}

class Payment {
    private final String paymentId;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private LocalDateTime timestamp;

    public Payment (BigDecimal amount, PaymentMethod method, PaymentStatus status, LocalDateTime timestamp) {
        this.paymentId = generatePaymentId();
        this.method = method;
        this.status = status;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public PaymentStatus getStatus() {
         return this.status;
    }

    private String generatePaymentId() {
        return "PAY-" + method + "" + System.currentTimeMillis();
    }
}

interface PaymentProcessor {
    Payment process(BigDecimal amount);
}

class CashPaymentProcessor implements PaymentProcessor {
    public Payment process(BigDecimal amount) {
        return new Payment(amount, PaymentMethod.CASH, PaymentStatus.SUCCESS, LocalDateTime.now());
    }
}

interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare);
}

class BaseStrategy implements FareStrategy {

    private static final Map<VehicleSize, BigDecimal> RATE_MAP = new HashMap<>();

    static {
        RATE_MAP.put(VehicleSize.SMALL, new BigDecimal("1.0"));
        RATE_MAP.put(VehicleSize.MEDIUM, new BigDecimal("2.0"));
        RATE_MAP.put(VehicleSize.LARGE, new BigDecimal("3.0"));
    }

    synchronized public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        VehicleSize vehicleSize = ticket.getVehicle().getSize();
        BigDecimal rate = RATE_MAP.getOrDefault(vehicleSize, BigDecimal.ONE);
        BigDecimal duration = ticket.calculateParkingDuration();

        return currentFare.add(rate.multiply(duration));
    }
}

class PeakHourStrategy implements FareStrategy {

    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    synchronized public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {

        if (this.isPeakHour(ticket.getEntryTime())) {
            return currentFare.multiply(PEAK_MULTIPLIER);
        }

        return currentFare;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hr = time.getHour();
        return (hr > 7 && hr < 9) || (hr > 14 && hr < 19);
    }
}

class FareCalculator {
    private final List<FareStrategy> fareStrategies;

    FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        if (ticket == null) {
            throw new RuntimeException("Ticket is null");
        }

        BigDecimal currentFare = BigDecimal.ZERO;

        for (FareStrategy strategy : fareStrategies) {
            currentFare = strategy.calculateFare(ticket, currentFare);
        }

        return currentFare;

    }
}

class ParkingLot {
    private final ParkingManager parkingManager;
    private FareCalculator fareCalculator;
    private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator, Map<PaymentMethod, PaymentProcessor> paymentProcessors) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
        this.paymentProcessors = paymentProcessors;
    }

    public Ticket enterVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = this.parkingManager.parkVehicle(vehicle);

        Ticket ticket = new Ticket(parkingSpot, vehicle, LocalDateTime.now());
        return ticket;
    }

    public void exitVehicle(Ticket ticket, PaymentMethod paymentMethod) {
        if (ticket == null) return;

        parkingManager.unpark(ticket.getVehicle());

        ticket.setExitTime(LocalDateTime.now());

        BigDecimal fare = fareCalculator.calculateFare(ticket);

        PaymentProcessor paymentProcessor = paymentProcessors.get(paymentMethod);
        if (paymentProcessor == null) {
            throw new RuntimeException("Invalid payment gateway");
        }

        Payment payment = paymentProcessor.process(fare);
        if (payment == null) {
            throw new RuntimeException("payment failed");
        }

        ticket.setPayment(payment);

        System.out.println("Payment success, Thank you.");
    }
}


public class Main {

    public static void main() {

        Map<PaymentMethod, PaymentProcessor> paymentProcessors = new HashMap<>();

        paymentProcessors.put(PaymentMethod.CASH, new CashPaymentProcessor());

        List<FareStrategy> fareStrategies = new ArrayList<>();

        fareStrategies.add(new BaseStrategy());
        fareStrategies.add(new PeakHourStrategy());

        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingSpot p1 = new ParkingSpotImpl(101, VehicleSize.LARGE);
        floor1.addSpot(p1);

        ParkingFloor floor2 = new ParkingFloor(2);
        ParkingSpot p2 = new ParkingSpotImpl(201, VehicleSize.MEDIUM);
        floor2.addSpot(p2);

        List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(floor1);
        parkingFloors.add(floor2);

        ParkingManager parkingManager = new ParkingManager(parkingFloors);

        FareCalculator fareCalculator = new FareCalculator(fareStrategies);

        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator, paymentProcessors);


        Vehicle vehicle = new Truck("AP-39-AZ-1010");

        Ticket ticket = parkingLot.enterVehicle(vehicle);

        parkingLot.exitVehicle(ticket, PaymentMethod.CASH);

    }

}
