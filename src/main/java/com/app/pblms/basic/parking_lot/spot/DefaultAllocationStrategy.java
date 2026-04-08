
class DefaultAllocationStrategy implements SpotAllocationStrategy {

    @Override
    public ParkingSpot allocate
    (
        Vehicle vehicle,
        Map<VehicleSize, Queue<ParkingSpot>> freeSpots
    ) {

        VehicleSize userSize = vehicle.getSize();

        for (VehicleSize size : VehicleSize.values()) {

            if (size.ordinal() < userSize.ordinal()) continue;

            Queue<ParkingSpot> queue = freeSpots.get(size);
            ParkingSpot parkingSpot = queue.poll();

            if (parkingSpot != null && parkingSpot.canFitVehicle(vehicle)) {
                return parkingSpot;
            }
        }

        return null;

    }
}