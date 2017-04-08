package se.webstep.iotr.api;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.UUID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@SuppressWarnings({"unused", "unchecked", "SpringAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping(value = "/ranger")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)

public class RangerApi {


//    @Inject
//    private TimerService timerService;




    @RequestMapping(method = GET, produces = APPLICATION_JSON)
    public ResponseEntity doGet() {
//        Optional<TimerEntity> entity = timerService.getTimer(uuid);
//        return entity.isPresent() ? ok(new TimerFetch(entity.get())) : notFound();
        return new ResponseEntity(OK);
    }




    @RequestMapping(value = "/list", method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity fetch(@RequestBody @Valid List<UUID> uuids) {
//        List<TimerEntity> entities = timerService.getTimers(uuids);
//        return ok(entities.stream().map(TimerFetch::new).collect(toList()));
        return null;
    }




    @RequestMapping(method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity store(/*@RequestBody @Valid TimerCreate timerCreate*/) {
//        TimerEntity newEntity = timerService.saveNewTimer(timerCreate.getName(), timerCreate.getDescription(), timerCreate.getDeadline());
//        TimerFetch createdTimer = new TimerFetch(newEntity);
//        return ok(createdTimer);
        return null;
    }




    @RequestMapping(method = PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity update(@RequestBody @Valid TimerUpdate timerUpdate) {
//        Optional<TimerEntity> updatedEntity = timerService.updateTimer(timerUpdate.getUuid(), timerUpdate.getName(), timerUpdate.getDescription(), timerUpdate.getDeadline());
//        return updatedEntity.isPresent() ? ok(new TimerFetch(updatedEntity.get())) : notFound();
        return null;
    }




    @RequestMapping(value = "{uuid}", method = DELETE)
    public ResponseEntity delete(@PathVariable("uuid") @Valid UUID uuid) {
//        return timerService.deleteTimer(uuid) ? ok() : notFound();
        return null;
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
