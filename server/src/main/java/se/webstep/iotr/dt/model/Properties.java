package se.webstep.iotr.dt.model;

import java.time.ZonedDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class Properties {

    private int temperature;

    private boolean object_present;

    private boolean touch;

    private ZonedDateTime last_pressed;

    private int rssi;

    private int rssi_strength;

    private float battery_voltage;

    private float battery_percentage;

    private String connector_id;


    public Properties() {
    }


    public int getTemperature() {
        return temperature;
    }


    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }


    public boolean isObject_present() {
        return object_present;
    }


    public void setObject_present(boolean object_present) {
        this.object_present = object_present;
    }


    public boolean isTouch() {
        return touch;
    }


    public void setTouch(boolean touch) {
        this.touch = touch;
    }


    public ZonedDateTime getLast_pressed() {
        return last_pressed;
    }


    public void setLast_pressed(ZonedDateTime last_pressed) {
        this.last_pressed = last_pressed;
    }


    public int getRssi() {
        return rssi;
    }


    public void setRssi(int rssi) {
        this.rssi = rssi;
    }


    public int getRssi_strength() {
        return rssi_strength;
    }


    public void setRssi_strength(int rssi_strength) {
        this.rssi_strength = rssi_strength;
    }


    public float getBattery_voltage() {
        return battery_voltage;
    }


    public void setBattery_voltage(float battery_voltage) {
        this.battery_voltage = battery_voltage;
    }


    public float getBattery_percentage() {
        return battery_percentage;
    }


    public void setBattery_percentage(float battery_percentage) {
        this.battery_percentage = battery_percentage;
    }


    public String getConnector_id() {
        return connector_id;
    }


    public void setConnector_id(String connector_id) {
        this.connector_id = connector_id;
    }


    @Override
    public String toString() {
        return "Properties{" +
                "temperature=" + temperature +
                ", object_present=" + object_present +
                ", touch=" + touch +
                ", last_pressed=" + last_pressed +
                ", rssi=" + rssi +
                ", rssi_strength=" + rssi_strength +
                ", battery_voltage=" + battery_voltage +
                ", battery_percentage=" + battery_percentage +
                ", connector_id='" + connector_id + '\'' +
                '}';
    }
}
