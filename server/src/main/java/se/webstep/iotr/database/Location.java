package se.webstep.iotr.database;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashSet;
import java.util.Set;

public class Location {


    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String name;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Set<Registration> registrations;


    public Location() {
        this.registrations = new HashSet<>();
    }


    public Location(String name) {
        this();
        this.name = name;
    }

    public Location copy() {

        Location copy = new Location();
        copy.setName(this.getName());
        copy.registrations = new HashSet<>();
        return copy;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Set<Registration> getRegistrations() {
        return registrations;
    }


    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return name.equals(location.name);
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                '}';
    }
}
