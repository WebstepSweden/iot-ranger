package se.webstep.iotr.database;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

public class Database {


    private static Database instance;

    private Set<Location> locations;

    private Set<Registration> registrations;



    private Database() {

        this.locations = new HashSet<>();
        this.registrations = new HashSet<>();
        insertTestData();

    }


    private void insertTestData() {

        addLocation("Lobby");
        addLocation("Boiler Room");
        addLocation("Parking");
        addLocation("Tee 1");

        String id = "206881543";
        LocalDateTime now = LocalDateTime.now();
        register(id, now, "Lobby");
        register(id, now.minusSeconds(1), "Lobby");
        register(id, now.minusSeconds(2), "Lobby");
        register(id, now.minusSeconds(3), "Lobby");
        register(id, now, "Boiler Room");
        register(id, now.minusSeconds(3), "Boiler Room");
        register(id, now, "Parking");
        register(id, now.minusSeconds(4), "Parking");
        register(id, now, "Tee 1");
        register(id, now.minusMinutes(3).minusSeconds(12), "Tee 1");

    }



    public static Database instance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }




    public Set<Location> getLocations() {
        return locations;
    }




    public boolean addLocation(String name) {

        Location location = new Location(name);

        if (locationExists(name)) {
            return false;
        }

        this.locations.add(location);

        return true;
    }


    public Location getLocation(String name) {
        Optional<Location> loc = locations.stream().filter(l -> l.getName().equals(name)).findFirst();
        if(!loc.isPresent()) {
            return null;
        }

        Location location = loc.get().copy();

        Set<Registration> locRegistrations = registrations.stream().filter(registration -> registration.getLocation().equals(location.getName())).collect(toSet());
        location.setRegistrations(locRegistrations);

        return location;

    }


    public boolean deleteLocation(String name) {
        Optional<Location> loc = locations.stream().filter(l -> l.getName().equals(name)).findFirst();
        if(!loc.isPresent()) {
            return false;
        }

        locations.remove(loc.get());
        return true;

    }


    public boolean locationExists(String name) {
        return locations.contains(new Location(name));
    }



    public Registration register(String id, LocalDateTime timestamp, String locationName) {

        Optional<Location> location = locations.stream().filter(l -> l.getName().equals(locationName)).findFirst();
        if(!location.isPresent()) {
            return null;
        }

        Registration registration = new Registration(id, timestamp, location.get().getName());
        registrations.add(registration);
        return registration;
        
    }




}
