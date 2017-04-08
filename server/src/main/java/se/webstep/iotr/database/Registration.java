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




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }




    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }




    @Override
    public String toString() {
        return "Registration{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", location=" + location +
                '}';
    }
}
