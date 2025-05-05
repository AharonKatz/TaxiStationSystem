public class Main {
    public static void main(String[] args) {
        TaxiDriver alice = new TaxiDriver("Alice", true);
        TaxiDriver bob = new TaxiDriver("Bob", true);
        TaxiStation station = new TaxiStation(java.util.Arrays.asList(alice, bob));

        Customer john = new Customer("John");
        Customer emma = new Customer("Emma");

        john.requestRide(station, new Location(0, 0), new Location(3, 4), 5.0);
        emma.requestRide(station, new Location(1, 1), new Location(2, 2), 1.4);
        john.requestRide(station, new Location(2, 3), new Location(5, 7), 5.0);

        station.processNextRide();
        station.processNextRide();
        station.processNextRide();
        station.processNextRide();
    }
}