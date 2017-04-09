package se.webstep.iotr.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import se.webstep.iotr.database.Database;
import se.webstep.iotr.database.Registration;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@SuppressWarnings({"unused", "unchecked", "SpringAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping(value = "/ranger")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)

public class RangerApi {


    // 206881543

    @RequestMapping(value = "register", method = PUT, produces = APPLICATION_JSON)
    public ResponseEntity register(@RequestParam(name = "id") String id,
                                      @RequestParam(name = "timestamp") @DateTimeFormat(iso = DATE_TIME) LocalDateTime timestamp,
                                      @RequestParam(name = "location") String location) {

        RestTemplate rt = new RestTemplate();

        HttpEntity entity = new HttpEntity<>(null, headers());

        System.out.println(String.format("Register, id: %s, timestamp: %s, location: %s", id, timestamp, location));

        Registration registration = Database.instance().register(id, timestamp, location);
        return new ResponseEntity(registration, OK);

    }


    @RequestMapping(value = "location", method = PUT, produces = APPLICATION_JSON)
    public ResponseEntity addLocation(@RequestParam(name = "name") String name) {


        if (Database.instance().addLocation(name)) {
            return new ResponseEntity(OK);
        } else {
            return new ResponseEntity(CONFLICT);
        }

    }


    @RequestMapping(value = "locations", method = GET, produces = APPLICATION_JSON)
    public ResponseEntity getLocations() {

        return new ResponseEntity(Database.instance().getLocations(), OK);

    }

    @RequestMapping(value = "location", method = GET, produces = APPLICATION_JSON)
    public ResponseEntity getLocation(@RequestParam(name="location") String locationName) {

        return new ResponseEntity(Database.instance().getLocation(locationName), OK);

    }


    @RequestMapping(value = "location", method = DELETE, produces = APPLICATION_JSON)
    public ResponseEntity deleteLocation(@RequestParam(name="location") String locationName) {

        boolean deleted = Database.instance().deleteLocation(locationName);

        return new ResponseEntity(deleted ? OK : NOT_FOUND);

    }






    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "ApiKey d45a7c14c88f48f5937a8fc3254378ad");
        return headers;
    }







    private ResponseEntity ok() {
        return new ResponseEntity(OK);
    }




    private ResponseEntity ok(Object object) {
        return new ResponseEntity(object, OK);
    }




    private ResponseEntity notFound() {
        return new ResponseEntity(NOT_FOUND);
    }




    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            ValidationException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
    protected void badRequestExceptions() {
    }


}

//        ResponseEntity<JsonNode> json= rt.exchange("https://api.disruptive-technologies.com/v1/things", HttpMethod.GET, entity, JsonNode.class);