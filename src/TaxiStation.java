import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaxiStation {
    private Queue<Ride> rideQueue = new PriorityQueue<>();
    private List<TaxiDriver> drivers;

    public TaxiStation(List<TaxiDriver> drivers) {
        this.drivers = drivers;
    }

    public void enqueueRide(Ride ride) {
        rideQueue.add(ride);
        System.out.println("Ride enqueued: " + ride.getCustomerName() + " from " + ride.getPickupLocation() + " to " + ride.getDropOffLocation() + " (" + ride.getDistance() + "km)");
    }

    public void processNextRide() {
        if (!rideQueue.isEmpty()) {
            Ride ride = rideQueue.poll();
            TaxiDriver driver = assignDriver();
            if (driver != null) {
                ride.setStatus("ASSIGNED");
                System.out.println("Processing ride for " + ride.getCustomerName() + " (Distance: " + ride.getDistance() + "km, Timestamp: " + ride.getTimestamp() + ")");
                driver.handleRide(ride);
            } else {
                rideQueue.add(ride);
            }
        } else {
            System.out.println("No rides to process.");
        }
    }

    private TaxiDriver assignDriver() {
        TaxiDriver leastBusy = null;
        int minRides = Integer.MAX_VALUE;
        for (TaxiDriver driver : drivers) {
            if (driver.isAvailable() && driver.getActiveRides() < minRides) {
                leastBusy = driver;
                minRides = driver.getActiveRides();
            }
        }
        if (leastBusy != null) {
            leastBusy.setAvailable(false);
            System.out.println("Assigned driver: " + leastBusy.getName());
        } else {
            System.out.println("No available drivers right now.");
        }
        return leastBusy;
    }

    public void releaseDriver(TaxiDriver driver, Ride ride) {
        driver.setAvailable(true);
        driver.decrementActiveRides();
        System.out.println("Driver " + driver.getName() + " completed ride for " + ride.getCustomerName() + " and is now available.");
    }
}