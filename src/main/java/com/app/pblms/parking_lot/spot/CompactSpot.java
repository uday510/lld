package com.app.pblms.parking_lot.spot;

import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class CompactSpot extends AbstractParkingSpot {
    public CompactSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
    
}
