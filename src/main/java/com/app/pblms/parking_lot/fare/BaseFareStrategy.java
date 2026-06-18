package com.app.pblms.parking_lot.fare;

import com.app.pblms.parking_lot.ticket.Ticket;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import java.math.BigDecimal;
import java.util.Map;

public class BaseFareStrategy implements FareStrategy {

    static final Map<VehicleSize, BigDecimal> RATE_MAP = Map.of(
        VehicleSize.SMALL, new BigDecimal("1"),
        VehicleSize.MEDIUM, new BigDecimal("2"),
        VehicleSize.LARGE, new BigDecimal("3")
    );

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {

        long minutes = ticket.getParkingDurationMinutes();

        VehicleSize size = ticket.getVehicle().getVehicleSize();

        BigDecimal rate = RATE_MAP.getOrDefault(size, BigDecimal.ONE);

        return currentFare.add(rate.multiply(BigDecimal.valueOf(minutes)));
    }

}
