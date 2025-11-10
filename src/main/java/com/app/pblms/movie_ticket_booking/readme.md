# 🎬 Movie Booking System – Low-Level Design (LLD)

This document summarizes the **core concepts, flow, and design patterns** for a Movie Booking System (like BookMyShow, Fandango).  
It’s optimized for **interview prep** — you can revise it in 5–10 minutes before your LLD/system design rounds.

---

## 🚀 Overview

The system allows users to:
- Browse movies, theatres, and showtimes.
- View available seats.
- Lock seats temporarily (concurrency-safe).
- Confirm booking via payment.
- Generate tickets and handle cancellations.

---

## 🧩 Core Entities

| Entity | Description |
|--------|--------------|
| **Movie** | Title, duration, language |
| **Theatre** | Name, city, list of screens |
| **Screen** | Contains multiple seats |
| **Seat** | Seat number, type (Regular/Premium/VIP), status |
| **Show** | Movie, screen, start/end time, pricing map |
| **Booking** | Selected seats for a show, total amount, status |
| **Payment** | Payment status and transaction details |
| **Ticket** | Generated after booking confirmation |

---

## ⚙️ Services & Responsibilities

| Service | Role |
|----------|------|
| **MovieService** | Fetch movies & metadata |
| **TheatreService** | Manage theatres, screens & rooms |
| **ScreeningService** | Manage shows and available seats |
| **SeatLockService** | Lock/unlock seats (handles concurrency) |
| **BookingService** | Create, confirm, or cancel bookings |
| **PaymentService** | Process payments and confirm status |
| **PricingStrategy** | Calculate seat prices dynamically |

---

## 🔐 Concurrency Handling

- **SeatLockService** prevents double booking using TTL-based locks.
- Can be implemented with:
    - Redis (key = showId + seatId)
    - DB row-level locks
- Auto unlock after timeout or payment failure.

---

## 💳 Booking Flow

1. **User selects movie → show → seats**
2. **SeatLockService** locks the seats temporarily.
3. **BookingService** creates a booking with `PENDING` status.
4. **PaymentService** processes payment.
5. On success → mark seats as `BOOKED`, generate `TICKET`.
6. On failure → release seat locks.
7. Optional: user cancels booking → seats become `AVAILABLE`.

---

## 💡 Design Patterns Used

| Pattern | Usage |
|----------|--------|
| **Strategy** | PricingStrategy for dynamic seat pricing |
| **Singleton** | SeatLockService (shared state) |
| **Factory** | PaymentService provider selection |
| **Observer (optional)** | For sending notifications |

---

## 🧱 Class Structure (Simplified)

```java
class Movie { Long id; String title; Duration duration; }
class Theatre { Long id; String name; String city; List<Screen> screens; }
class Screen { Long id; String name; List<Seat> seats; }
class Seat { Long id; String seatNumber; SeatType type; SeatStatus status; }

class Show { Long id; Movie movie; Screen screen; LocalDateTime start; Map<SeatType, Double> seatPricing; }
class Booking { Long id; Show show; List<Seat> seats; double totalAmount; BookingStatus status; Payment payment; }

class SeatLockService { Map<Long, Long> lockedSeats; void lockSeats(...); void unlockSeats(...); }
class BookingService { Booking createBooking(...); void confirmBooking(...); void cancelBooking(...); }
class PaymentService { Payment processPayment(...); }