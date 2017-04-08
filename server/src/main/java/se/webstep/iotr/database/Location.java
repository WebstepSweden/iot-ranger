package se.webstep.iotr.database;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Location {

    
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String name;




    public Location() {
    }




    public Location(String name) {
        this.name = name;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
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
