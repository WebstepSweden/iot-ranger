package se.webstep.iotr.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class Main {

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        try {
//            Sensors sensors = new DtClient().getSensors();
//            Sensor sensor = new DtClient().getSensor("206881543");
            Sensor sensor = new DtClient().watch("206881543");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}