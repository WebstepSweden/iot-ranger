package se.webstep.iotr.database;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import se.webstep.iotr.api.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Registration {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String id;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String location;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private boolean inRange;


    public Registration() {
    }


    public Registration(String id, LocalDateTime timestamp, String location) {

        this.id = id;
        this.timestamp = timestamp;
        this.location = location;

    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public boolean isInRange() {
        return inRange;
    }


    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (inRange != that.inRange) return false;
        if (!id.equals(that.id)) return false;
        if (!timestamp.equals(that.timestamp)) return false;
        return location.equals(that.location);
    }


    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (inRange ? 1 : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Registration{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", location='" + location + '\'' +
                ", inRange=" + inRange +
                '}';
    }
}
