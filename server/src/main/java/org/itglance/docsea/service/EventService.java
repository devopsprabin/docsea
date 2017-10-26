package org.itglance.docsea.service;

import org.itglance.docsea.domain.Event;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.repository.EventRepository;
import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.service.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sriyanka on 6/12/17.
 */

@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final HospitalRepository hospitalRepository;

    @Autowired
    private SessionService sessionService;


    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
    Date d = dateFormatter.parse(dateFormatter.format(new Date() ));
    public EventService(EventRepository eventRepository, HospitalRepository hospitalRepository) throws ParseException {
        this.eventRepository = eventRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public void addEvent(EventDTO eventDTO, String token){
        //Event event1=eventRepository.isEventExist(hospitalId,eventDTO.getDates());
        Long hospitalId = sessionService.checkSession(token).getHospitalId();

        Event event=new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDates(eventDTO.getDates());
        event.setTime(eventDTO.getTime());
        event.setPhoto(eventDTO.getPhoto());
        event.setLocation(eventDTO.getLocation());

        Hospital hospital=hospitalRepository.findOne(hospitalId);
        event.setHospital(hospital);
        System.out.println("**********************************************************");
        System.out.println(event.toString());

        eventRepository.save(event);

    }

    public boolean updateHospital(EventDTO eventDTO){
        Event event=eventRepository.findOne(eventDTO.getId());
        if(event!=null){
            event.setName(eventDTO.getName());
            event.setDescription(eventDTO.getDescription());
            event.setDates(eventDTO.getDates());
            event.setTime(eventDTO.getTime());
            event.setPhoto(eventDTO.getPhoto());
            event.setLocation(eventDTO.getLocation());
            eventRepository.save(event);
            return true;
        }
        return false;



    }

    public boolean isEventExist(String token, Date dates, String name, String time){
        Long id = sessionService.checkSession(token).getHospitalId();
        Event event= eventRepository.isEventExist(id, dates, name, time);
        if(event!=null){
            return true;
        }
        else{
            return false;
        }

    }


    public List<Event> getAllValidEvents() {

        List<Event> allValidEvents = eventRepository.findAllValidEvent(d);
        return allValidEvents;
    }

    public List<Event> getAllValidEventsOfHospital(String token) {
        Long hospitalId = sessionService.checkSession(token).getHospitalId();
        List<Event> allValidEvents = eventRepository.findAllValidEventsOfhospital(d, hospitalId);
        return allValidEvents;
    }

    public Event getEvent(Long eventId) {
        Event event = eventRepository.getOne(eventId);
        return event;
    }
}


