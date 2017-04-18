package se.webstep.iotr.dt.model;

import java.time.ZonedDateTime;

public class Thing {

    private String id;

    private String name;

    private String description;

    private boolean starred;

    private ZonedDateTime registered;

    private ZonedDateTime last_updated;

    private State state;

    private Type type;

    private String firmware;


    public Thing() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isStarred() {
        return starred;
    }


    public void setStarred(boolean starred) {
        this.starred = starred;
    }


    public ZonedDateTime getRegistered() {
        return registered;
    }


    public void setRegistered(ZonedDateTime registered) {
        this.registered = registered;
    }


    public ZonedDateTime getLast_updated() {
        return last_updated;
    }


    public void setLast_updated(ZonedDateTime last_updated) {
        this.last_updated = last_updated;
    }


    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }


    public Type getType() {
        return type;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public String getFirmware() {
        return firmware;
    }


    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }


    @Override
    public String toString() {
        return "Thing{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", starred=" + starred +
                ", registered=" + registered +
                ", last_updated=" + last_updated+
                ", state=" + state +
                ", type=" + type +
                ", firmware='" + firmware + '\'' +
                '}';
    }
}
