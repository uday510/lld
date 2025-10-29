package com.app.pblms.parking_lot.spot;

import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class HandicappedSpot extends AbstractParkingSpot {
    public HandicappedSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

}
