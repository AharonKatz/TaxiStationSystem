public class TaxiDriver {
    private String name;
    private boolean available;
    private int activeRides;

    public TaxiDriver(String name, boolean available) {
        this.name = name;
        this.available = available;
        this.activeRides = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getActiveRides() {
        return activeRides;
    }

    public void handleRide(Ride ride) {
        activeRides++;
        ride.setStatus("IN_PROGRESS");
        new Thread(() -> {
            try {
                int rideTime = (int)(ride.getDistance() * 1000) + 1000;
                Thread.sleep(rideTime);
                ride.setStatus("COMPLETED");
                System.out.println(name + " completed ride for " + ride.getCustomerName() + " (" + ride.getDistance() + "km) after " + rideTime + "ms");
                ride.getStation().releaseDriver(this, ride);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(name + " is driving " + ride.getCustomerName() + " from " + ride.getPickupLocation() + " to " + ride.getDropOffLocation());
    }

    public void decrementActiveRides() {
        activeRides--;
    }
}