# Parking Lot System Design

## 1. System Overview
A parking lot is a designated area for parking vehicles found in venues like:
- Shopping malls
- Sports stadiums
- Office buildings

### Key Features:
- Fixed number of parking spots for different vehicle types
- Time-based charging system
- Ticket-based parking duration tracking
- Multiple payment options (automated/agent, card/cash)

## 2. Core Requirements

| ID | Requirement Description |
|----|-------------------------|
| R1 | 40,000 vehicle capacity |
| R2 | 4 spot types: Handicapped, Compact, Large, Motorcycle |
| R3 | Multiple entrance/exit points |
| R4 | Vehicle types: Car, Truck, Van, Motorcycle |
| R5 | Prevent overcapacity parking |
| R7 | Display "FULL" messages when capacity reached |
| R8 | Ticket at entrance, payment at exit |
| R9 | Payment options: automated panel or agent |
| R10 | Hourly rate calculation |
| R11 | Payment methods: credit/debit card or cash |

## 3. Design Expectations

### Payment Flexibility
- Multiple payment points (exit panels/agents)
- Various payment methods (cash, credit, coupons)
- Multi-floor payment tracking

### Parking Spot Types
- Handicapped
- Compact
- Large
- Motorcycle

**Key Considerations:**
- Capacity management per type
- Full lot handling
- Multi-floor spot tracking
- Spot type distribution

### Vehicle Types
- Car
- Truck
- Van
- Motorcycle

**Key Considerations:**
- Capacity allocation
- Cross-type parking rules

### Pricing Model
- Tiered hourly rates (e.g., $4 → $3.5 → $2.5)
- Vehicle-type pricing differentiation

## 4. Design Approach
**Bottom-Up Methodology:**
1. Design small components (vehicles, spots)
2. Combine into larger systems (payment, allocation)
3. Iterate to complete system

**Recommended Design Patterns:**
- Factory Pattern (object creation)
- Observer Pattern (capacity monitoring)
- Strategy Pattern (payment methods)

## 5. Use Case Analysis

### Actors
**Primary:**
- Customer (parks/pays)
- Parking Agent (assists customers)

**Secondary:**
- Admin (manages system)
- System (automated processes)

### Use Cases

**Admin:**
- Add/remove spots
- Manage agents
- Modify rates
- Update panels
- Account management

**Customer:**
- Take ticket
- Scan ticket
- Pay ticket (Cash/Card)
- Park vehicle

**Parking Agent:**
- All customer use cases +
- Account updates
- Login/logout

**System:**
- Assign spots
- Remove spots
- Display status
- Show availability

### Relationships
**Generalizations:**
1. Parking Agent → Customer
2. Pay Ticket → (Cash, Credit Card)

## 6. Class Diagram Components
```mermaid
classDiagram
    class Vehicle {
        +String licensePlate
        +VehicleType type
    }
    
    class ParkingSpot {
        +SpotType type
        +Boolean isOccupied
        +assignVehicle()
        +removeVehicle()
    }
    
    class Ticket {
        +DateTime entryTime
        +DateTime exitTime
        +calculateFee()
    }
    
    class Payment {
        +processPayment()
    }
    
    Vehicle "1" -- "1" Ticket
    ParkingSpot "1" -- "1" Vehicle
    Ticket "1" -- "1" Payment
