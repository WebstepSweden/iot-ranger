package se.webstep.iotr.service;


import org.springframework.stereotype.Component;
import se.webstep.iotr.database.Registration;
import se.webstep.iotr.database.Sensor;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SuppressWarnings("SpellCheckingInspection")
@Component
public class Reconsiliator {

    public boolean match(Registration registration, Sensor sensorState, long toleranceMillis) {
        if(!sensorState.getId().equals(registration.getId())) {
            // Not the same sensor nor registration
            return false;
        }

        LocalDateTime regDateTime = registration.getTimestamp();

        LocalDateTime sensorDateTime = sensorState.getLastPressed();

        if(sensorDateTime == null) {
            return false;
        }

        long regMillis = regDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long sensorMills = sensorDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        boolean timeWithinRange = Math.abs(regMillis-sensorMills) < toleranceMillis;

        return timeWithinRange;


    }

}
