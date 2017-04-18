package se.webstep.iotr.dt.model;

import java.time.ZonedDateTime;

public class State {

    private String id;

    private ZonedDateTime updated;

    private Properties properties;


    public State() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public ZonedDateTime getUpdated() {
        return updated;
    }


    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }


    public Properties getProperties() {
        return properties;
    }


    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", updated=" + updated +
                ", properties=" + properties +
                '}';
    }
}
