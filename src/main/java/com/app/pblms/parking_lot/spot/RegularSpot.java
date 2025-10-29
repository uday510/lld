package com.app.pblms.parking_lot.spot;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class RegularSpot extends AbstractParkingSpot {
    public RegularSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}
