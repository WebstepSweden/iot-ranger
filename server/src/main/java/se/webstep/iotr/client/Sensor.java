package se.webstep.iotr.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {

    private String id;
    private String name;
    private String description;
    private boolean starred;
    private ZonedDateTime registered;
    @JsonProperty("last_updated")
    private ZonedDateTime lastUpdated;

    private State state;
    private Type type;

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

    public ZonedDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(ZonedDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
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


    /**
     "state": {
     "id": "206881543",
     "updated": "2017-04-08T21:30:22.586920970Z",
     "properties": {
     "temperature": 20,
     "object_present": false,
     "touch": false,
     "last_pressed": "2017-04-08T16:58:45.086921291Z",
     "rssi": -90,
     "rssi_strength": 2,
     "battery_voltage": 2.97,
     "battery_percentage": 100,
     "connector_id": "con-000"
     }
     */
    private static class State {
        private String id;
        private ZonedDateTime updated;
        private Map<String, Object> properties;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ZonedDateTime getUpdated() {
            return updated;
        }

        public void setUpdated(ZonedDateTime updated) {
            this.updated = updated;
        }

        public Map<String, Object> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, Object> properties) {
            this.properties = properties;
        }

        @Override
        public String toString() {
            return "State{" +
                    "id='" + id + '\'' +
                    ", updated=" + updated +
                    ", properties=" + properties +
                    '}';
        }
    }

    /**
     "type": {
     "id": "touch",
     "name": "",
     "icon": "",
     "chart_type": "",
     "primary": ""
     },
     "firmware": ""
     */
    private static class Type {
        private String id;
        private String name;
        private String icon;
        @JsonProperty("chart_type")
        private String chartType;
        private String primary;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getChartType() {
            return chartType;
        }

        public void setChartType(String chartType) {
            this.chartType = chartType;
        }

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    ", chartType='" + chartType + '\'' +
                    ", primary='" + primary + '\'' +
                    '}';
        }
    }
}