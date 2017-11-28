package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.Event;
import org.itglance.docsea.service.EventService;
import org.itglance.docsea.service.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sriyanka on 6/12/17.
 */

@CrossOrigin
@RestController
@RequestMapping(value="/api/events")
public class EventController {



    @Autowired
    private  EventService eventService;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
    Date d = dateFormatter.parse(dateFormatter.format(new Date() ));

    public EventController() throws ParseException {
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<String> addEvents(@RequestBody EventDTO eventDTO,
                                       @RequestHeader String Authorization) throws ParseException {
        if (!(eventService.isEventExist(Authorization, eventDTO.getDates(), eventDTO.getName(), eventDTO.getTime()))) {


            System.out.println("**************this is event dto*******************");
            System.out.println(eventDTO.toString());
            System.out.println("**************this is event dto*******************");

            if(eventDTO.getDates().before(d)){
               return new ResponseEntity<>("Invalid date", HttpStatus.BAD_REQUEST);
            }
            eventService.addEvent(eventDTO, Authorization);
            return new ResponseEntity<>("Inserted sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event already exist", HttpStatus.CONFLICT);
        }
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> list = eventService.getAllValidEvents();
        if (list.isEmpty()) {
            return new ResponseEntity("There are no events",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    //returns all events of particular hospital
    @GetMapping(value = "/hospital")
    public ResponseEntity<List<Event>> listAllEventsOfHospital(@RequestHeader String Authorization) {
        List<Event> list = eventService.getAllValidEventsOfHospital(Authorization);
        if (list.isEmpty()) {
            return new ResponseEntity("There is no events",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //returns one event
    @GetMapping(value = "/{eventId}")
    public ResponseEntity<Event> listAllEvents(@RequestParam("eventId") Long eventId) {
        Event event = eventService.getEvent(eventId);
        if (event == null ) {
            return new ResponseEntity("There is no event of eventId: "+eventId,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateEvent( @RequestBody EventDTO eventDTO) {
        if(eventDTO.getDates().before(d)){
            return new ResponseEntity<>("Invalid date",HttpStatus.BAD_REQUEST);
        }
        if (eventService.updateHospital(eventDTO)){
           return new ResponseEntity<>("Update successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event doesn't exist", HttpStatus.CONFLICT);
        }
    }
}
