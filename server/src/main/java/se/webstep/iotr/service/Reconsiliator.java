package se.webstep.iotr.service;


import org.springframework.stereotype.Component;
import se.webstep.iotr.database.Registration;
import se.webstep.iotr.database.Sensor;
import java.time.LocalDateTime;

@Component
public class Reconsiliator {

    public boolean match(Registration registration, Sensor sensorState, long toleranceMillis) {
        if(!sensorState.getId().equals(registration.getId())) {
            // Not the same sensor nor registration
            return false;
        }

        LocalDateTime regDateTime = registration.getTimestamp();

//        LocalDateTime sensorDateTime = sensorState.getLastUpdated().toLocalDateTime();

        return false;


    }

}
