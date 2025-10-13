package com.app.pblms.parkinglot.fare;

import com.app.pblms.parkinglot.ticket.Ticket;
import com.app.pblms.parkinglot.vehicle.Vehicle;
import com.app.pblms.parkinglot.vehicle.VehicleSize;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Applies base fare based on vehicle size and parking duration.
 */
public class BaseFareStrategy implements FareStrategy {

    private static final Map<VehicleSize, BigDecimal> RATE_MAP = new HashMap<>();

    static {
        RATE_MAP.put(VehicleSize.SMALL, new BigDecimal("1.0"));
        RATE_MAP.put(VehicleSize.MEDIUM, new BigDecimal("2.0"));
        RATE_MAP.put(VehicleSize.LARGE, new BigDecimal("3.0"));
    }

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        VehicleSize vehicleSize = ticket.getVehicle().getSize();
        BigDecimal rate = RATE_MAP.getOrDefault(vehicleSize, BigDecimal.ONE);
        BigDecimal duration = ticket.calculateParkingDuration();

        // fate = currentFare + rate * duration
        return currentFare.add(rate.multiply(duration));
    }

}
