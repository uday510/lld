Requirements: 

    - vehicle types: motorcycles(S) / car(M) / truck(L): smaller fits in larger
    - multi-floor, spots have sizes
    - pay on exit, by duration + vehicle size; peak-hour surcharge
    - multiple entry/exit gates -> CONCURRENT: no double-occupancy
    - in-memory, single JVM

Entities:

    Vehicle (abstract): plate, size
        -> motorcycle(S) | Car(M) | Truck(L)
    ParkingSpot: id, size, vehicle?     [occupy / vacate / isFree]
    ParkingFloor: floorNo, List<ParkingSpot>
    Ticket: vehicle, spot, entryTime, exitTime [getDurationMins]
    Payment: id, method, status, amount
    EntryGate / ExitGate: thins callers -> lot.park / lot.unpark
    ParkingLot: orchestrator (own all shared state + concurrency)

Enums:
    
    VehicleSize: SMALL, MEDIUM, LARGE
    PaymentMethod: CASH, CREDIT_CARD
    PaymentStatus: SUCCESS, PENDING, FAILED

Relationships:
    
    ParkingLot      has-many   ParkingFloor    (composition)
    ParkingFloor    has-many   ParkingSpot     (composition)
    ParkingSpot     has-one    Vehicle         (0..1)
    Vehicle         is-a       Moto/Car/Truck
    Ticket          refs       Vehicle, Spot

Interfaces:
    
    FareStrategy        -> BaseFare, PeakHour [chain]
    PaymentProcessor    -> Cash, CreditCard   [Map<Method, Processor> dispatch]


Core:
    
    freeSpots:      EnumMAP<VehicleSize, ConcurrentLinkedQueue<ParkingSpot>>
    vehicleStore:   ConcurrentHashMap<plate, ParkingSpot>
    park():        allocate = queue.poll() (atomic O(1))
                   claim = store.putIfAbsent(plate, spot)
                           if lost -> release spot back to queue
                    
                   occupy() -> if throws, remove from store + release (rollback)
            
    unpark():      store.remove(plate) (atomic gate -> double-unpark impossible)
                   fare BEFORE vacate; pay; if pay failes -> restore store entry
                   then vacate + release






    