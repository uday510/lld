
interface SpotAllocationStrategy {
    ParkingSpot allocate(Vehicle vehicle, Map<VehicleSize, Queue<ParkingSpot>> )
}