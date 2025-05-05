import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Ride> rideHistory;

    public Customer(String name) {
        this.name = name;
        this.rideHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }

    public void requestRide(TaxiStation station, Location pickup, Location dropOff, double distance) {
        Ride ride = new Ride(this, pickup, dropOff, distance, station);
        rideHistory.add(ride);
        station.enqueueRide(ride);
    }
}