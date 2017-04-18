package se.webstep.iotr.service;


import org.springframework.stereotype.Component;
import se.webstep.iotr.database.Registration;
import se.webstep.iotr.database.TouchEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SuppressWarnings("SpellCheckingInspection")
@Component
public class Reconsiliator {

    public boolean match(Registration registration, TouchEvent touchEvent, long toleranceMillis) {
        if(!touchEvent.getThingId().equals(registration.getId())) {
            // Not the same sensor nor registration
            return false;
        }

        LocalDateTime regDateTime = registration.getTimestamp();

        LocalDateTime sensorDateTime = touchEvent.getLastUpdated();

        if(sensorDateTime == null) {
            return false;
        }

        long regMillis = regDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long sensorMills = sensorDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        boolean timeWithinRange = Math.abs(regMillis-sensorMills) < toleranceMillis;

        return timeWithinRange;


    }

}
