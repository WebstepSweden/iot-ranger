package se.webstep.iotr.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import se.webstep.iotr.database.Database;
import se.webstep.iotr.database.TouchEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import static java.lang.String.format;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
@Component
public class Watcher {

    private ContinuousResponseExtractor responseExtractor;

    private Set<Consumer<JsonNode>> consumers;


    private Consumer<JsonNode> selfConsumer = jsonNode -> {

        System.out.println(format("Stage changed for thing: %s", jsonNode));


        Boolean isTouch = jsonNode.at("/result/state_changed/touch").asBoolean(false);

        Boolean isProximity = jsonNode.at("/result/state_changed/object_present").asBoolean(false);
        
        if (isTouch)  {
            System.out.println("Touch registered");
            String thingId = jsonNode.at("/result/thing_id").asText();
            LocalDateTime lastPressed = ZonedDateTime.parse(jsonNode.at("/result/state_changed/last_pressed").asText()).toLocalDateTime().atZone(ZoneId.systemDefault()).toLocalDateTime();
            TouchEvent te = new TouchEvent(thingId, lastPressed);
            if(thingId.equals("206762883")) {
            Database.instance().addTouchEvent(te);

            }
        } else if(isProximity) {
            System.out.println("Proximity registered");
        }
        
        consumers.forEach(otherConsumer -> {
            try {
                otherConsumer.accept(jsonNode);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

    };



    public Watcher() {
        this.consumers = new HashSet<>();
        this.responseExtractor = new ContinuousResponseExtractor(selfConsumer);
    }


    public void addConsumer(Consumer<JsonNode> consumer) {
        consumers.add(consumer);
    }


    public void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }


    @Async
    public void subscribeAll() {

        String url = "https://api.disruptive-technologies.com/v1/subscribe";
        RestTemplate rt = new RestTemplate();
        RequestCallback requestCallback = new WatcherRequestCallback("d45a7c14c88f48f5937a8fc3254378ad");
//        RequestCallback requestCallback = new WatcherRequestCallback("6310afd33c5a4c7fac0d30b51a5df1d5");
        JsonNode data = rt.execute(url, HttpMethod.GET, requestCallback, responseExtractor);

    }


}

/*

// api norsk gammal: 6310afd33c5a4c7fac0d30b51a5df1d5
// api svensk: d45a7c14c88f48f5937a8fc3254378ad

// touch: 206762883
// proximity: 206847491
// temp: 206858631

temp: 206858631
curl --request GET --url 'https://api.disruptive-technologies.com/v1/subscribe' --header 'accept: text/event-stream' --header 'Authorization: ApiKey 6310afd33c5a4c7fac0d30b51a5df1d5' --header 'cache-control: no-cache'

data:{
  "result":{
    "thing_id":"206847488",
    "event_type":"ThingStateChanged",
    "version":"31827",
    "timestamp":"2016-12-29T07:58:27.185116552Z",
    "state_changed":{
      "temperature":18.45,
      "object_present":false,
      "touch":false,
      "rssi":-91,
      "rssi_strength":1,
      "battery_voltage":2.88,
      "battery_percentage":60,
      "connector_id":"osl_Win_demoCC.demo1"
    }
  }
}

 */