package se.webstep.iotr.database;

import java.time.LocalDateTime;

public class TouchEvent {

    private String thingId;

    private LocalDateTime lastUpdated;


    public TouchEvent(String thingId, LocalDateTime lastUpdated) {
        this.thingId = thingId;
        this.lastUpdated = lastUpdated;
    }


    public String getThingId() {
        return thingId;
    }


    public void setThingId(String thingId) {
        this.thingId = thingId;
    }


    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }


    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TouchEvent that = (TouchEvent) o;

        if (!thingId.equals(that.thingId)) return false;
        return lastUpdated.equals(that.lastUpdated);
    }


    @Override
    public int hashCode() {
        int result = thingId.hashCode();
        result = 31 * result + lastUpdated.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "TouchEvent{" +
                "thingId='" + thingId + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
