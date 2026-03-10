import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

interface Vehicle {
    VehicleSize getSize();

    String getLicensePlate();
}

abstract class AbstractVehicle implements Vehicle {

    private final String licensePlate;

    public AbstractVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

class Motorcycle extends AbstractVehicle {

    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
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

class Truck extends AbstractVehicle {

    public Truck(String licensePlate) {
        super(licensePlate);
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

abstract class AbstractParkingSpot implements ParkingSpot {

    private final int spotNumber;
    private Vehicle vehicle;
    private boolean isAvailable;

    public AbstractParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        vehicle = null;
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public synchronized void occupy(Vehicle vehicle) {

        if (!isAvailable) {
            throw new IllegalStateException("Spot not available");
        }

        if (vehicle.getSize().ordinal() > getSize().ordinal()) {
            throw new IllegalArgumentException("Vehicle too large for spot");
        }

        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public synchronized void vacate() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }
}

class RegularSpot extends AbstractParkingSpot {

    public RegularSpot(int spotNumber) {
        super(spotNumber);
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

}

class Oversized extends AbstractParkingSpot {

    public Oversized(int spotNumber) {
        super(spotNumber);
    }

    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

}

class Handicapped extends AbstractParkingSpot {

    public Handicapped(int spotNumber) {
        super(spotNumber);
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

}

class Compact extends AbstractParkingSpot {

    public Compact(int spotNumber) {
        super(spotNumber);
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

}

class ParkingFloor {

    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

}

class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        this.ticketId = generateTicket();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setExitTime(LocalDateTime time) {
        this.exitTime = time;
    }

    private String generateTicket() {
        return "ticket_" + System.currentTimeMillis() + ( (int) Math.random() * 1000);
    }

    public BigDecimal calculateParkingDuration() {
        long min = Duration.between(entryTime, Objects.requireNonNullElse(exitTime, LocalDateTime.now())).toMinutes();
        return BigDecimal.valueOf(min);
    }


}

interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal amount);
}

class BaseFareStrategy implements FareStrategy {

    private static final Map<VehicleSize, BigDecimal> RATE_MAP = new HashMap<>();
    static {
        RATE_MAP.put(VehicleSize.SMALL, new BigDecimal("1.0"));
        RATE_MAP.put(VehicleSize.MEDIUM, new BigDecimal("2.0"));
        RATE_MAP.put(VehicleSize.LARGE, new BigDecimal("3.0"));
    }

    public BigDecimal calculateFare(Ticket ticket, BigDecimal amount) {
        VehicleSize size = ticket.getVehicle().getSize();
        BigDecimal rate = RATE_MAP.getOrDefault(size, BigDecimal.ONE);
        BigDecimal duration = ticket.calculateParkingDuration();

        return amount.add(rate.multiply(duration));
    }
}

class PeakHourFareStrategy implements FareStrategy {

    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    public BigDecimal calculateFare(Ticket ticket, BigDecimal amount) {

        LocalDateTime entryTime = ticket.getEntryTime();

        if (isPeakHour(entryTime)) {
            return amount.multiply(PEAK_MULTIPLIER);
        }

        return amount;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hr = time.getHour();
        return (
                (hr >= 7 && hr <= 10) ||
                        (hr >= 16 && hr <= 19)
                );
    }

}

class FareCalculator {

    private final List<FareStrategy> fareStrategies;

    public FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal fare = BigDecimal.ZERO;

        for (FareStrategy strategy : fareStrategies) {
            fare = strategy.calculateFare(ticket, fare);
        }

        return fare.setScale(2, RoundingMode.HALF_UP);
    }

}

enum PaymentMethod {
    CASH, CREDIT_CARD
}

enum PaymentStatus {
    PENDING, SUCCESS, FAILED
}

class Payment {

    private final String paymentId;
    private final PaymentMethod paymentMethod;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private final PaymentStatus status;

    public Payment(PaymentMethod paymentMethod, BigDecimal amount, LocalDateTime createdAt, PaymentStatus status) {
        this.paymentId = generatePaymentId();
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    private String generatePaymentId() {
        return "pay_" + System.currentTimeMillis();
    }

}

interface PaymentProcessor {
    Payment process(BigDecimal amount);
}

class CashPaymentProcessor implements PaymentProcessor {
    public Payment process(BigDecimal amount) {
        return new Payment(PaymentMethod.CASH, amount, LocalDateTime.now(), PaymentStatus.SUCCESS);
    }
}

class CreditCardPaymentProcessor implements PaymentProcessor {
    public Payment process(BigDecimal amount) {
        return new Payment(PaymentMethod.CREDIT_CARD, amount, LocalDateTime.now(), PaymentStatus.SUCCESS);
    }
}

class ParkingLot {

    private final Map<VehicleSize, Queue<ParkingSpot>> freeSpots;
    private final List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
        this.freeSpots = new EnumMap<>(VehicleSize.class);
    }

    private void initializeFreeSpots() {
     for (ParkingFloor floor : floors) {
         for (ParkingSpot spot : floor.getParkingSpots()) {
             freeSpots.get(spot.getSize()).offer(spot);
         }
     }

    }

}
