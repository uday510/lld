package com.app.pblms.parkinglot.fare;

import com.app.pblms.parkinglot.Ticket;
import com.app.pblms.parkinglot.vehicle.VehicleSize;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BaseFareStrategy implements FareStrategy {

    static final Map<VehicleSize, BigDecimal> RATE_MAP = new HashMap<>();
    static {
        RATE_MAP.put(VehicleSize.SMALL, new BigDecimal("1.0"));
        RATE_MAP.put(VehicleSize.MEDIUM, new BigDecimal("2.0"));
        RATE_MAP.put(VehicleSize.LARGE, new BigDecimal("3.0"));
    }
    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal amount) {
        VehicleSize vehicleSize = ticket.getVehicle().getSize();
        BigDecimal rate = RATE_MAP.getOrDefault(vehicleSize, BigDecimal.ONE);
        BigDecimal duration = ticket.calculateParkingDuration();

        // fare = currentFare + rate * duration
        return amount.add(rate.multiply(duration));
    }
}
