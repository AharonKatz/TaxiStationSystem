public class Ride implements Comparable<Ride> {
    private Customer customer;
    private Location pickupLocation;
    private Location dropOffLocation;
    private double distance;
    private String status;
    private long timestamp;
    private TaxiStation station;

    public Ride(Customer customer, Location pickupLocation, Location dropOffLocation, double distance, TaxiStation station) {
        this.customer = customer;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.distance = distance;
        this.status = "PENDING";
        this.timestamp = System.currentTimeMillis();
        this.station = station;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public double getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TaxiStation getStation() {
        return station;
    }

    @Override
    public int compareTo(Ride other) {
        int distanceCompare = Double.compare(this.distance, other.distance);
        return distanceCompare != 0 ? distanceCompare : Long.compare(this.timestamp, other.timestamp);
    }
}