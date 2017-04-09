package se.webstep.iotr.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensors {

    private List<Sensor> things;

    @JsonCreator
    public Sensors(@JsonProperty("things") List<Sensor> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "Sensors{" +
                "things=" + things +
                '}';
    }
}