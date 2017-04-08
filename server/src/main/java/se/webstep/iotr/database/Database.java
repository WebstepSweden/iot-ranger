package se.webstep.iotr.database;

import java.util.HashSet;
import java.util.Set;

public class Database {


    private static Database instance;

    private Set<Location> locations;




    private Database() {
        this.locations = new HashSet<>();
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




    public boolean locationExists(String name) {
        return locations.contains(new Location(name));
    }


}
