package org.itglance.docsea.web.rest;

import org.itglance.docsea.service.ScheduleService;
import org.itglance.docsea.service.SessionService;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.ScheduleDTO;
import org.itglance.docsea.service.dto.ScheduleStringDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sriyanka on 5/14/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/api/schedules")
public class ScheduleController {

    private final Logger log = LoggerFactory.getLogger(ScheduleController.class);
        @Autowired
        private ScheduleService scheduleService;
        @Autowired
        private SessionService sessionService;

        //return whole schedule of datatbase
        @GetMapping
        public ResponseEntity<?> getSchedule(){
            List<ScheduleDTO> scheduleDTO = scheduleService.getSchedule();
            if(scheduleDTO == null)
            {
                log.error("cannot find any schedule with schedule in database");
                return new ResponseEntity<String>("cannot find any schedule with schedule in database", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<List<ScheduleDTO>>(scheduleDTO, HttpStatus.OK);
        }

        // Returns schedule by schedule Id
        @GetMapping(value = "/{scheduleId}")
        public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") Long scheduleId){
            ScheduleDTO scheduleDTO = scheduleService.getScheduleById(scheduleId);
            if(scheduleDTO == null)
            {
                log.error("cannot find the schedule with schedule id: "+scheduleId);
                return new ResponseEntity<String>("cannot find the schedule with schedule id: "+scheduleId, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<ScheduleDTO>(scheduleDTO, HttpStatus.OK);
        }

        //Returns list of schedule of hospital by hospital Id
        @GetMapping(value = "/hospital/{hospitalId}")
        public ResponseEntity<?> getScheduleByHospitalId(@PathVariable("hospitalId") Long hospitalId){
            List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleByHospitalId(hospitalId);
            if(scheduleDTOS == null)
            {
                log.error("cannot find the schedule with hospital id: "+hospitalId);
                return new ResponseEntity<String>("cannot find the schedule with hospital id: "+hospitalId, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<List<ScheduleDTO>>(scheduleDTOS, HttpStatus.OK);
        }
    //Returns list of schedule of doctor by doctor Id
    @GetMapping(value = "/doctor/{doctorId}")
    public ResponseEntity<?> getScheduleByDoctorId(@PathVariable("doctorId") Long doctorId){
        List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleByDoctorId(doctorId);
        if(scheduleDTOS == null)
        {
            log.error("cannot find the schedule with doctor id: "+doctorId);
            return new ResponseEntity<String>("cannot find the schedule with doctor id: "+doctorId, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<ScheduleDTO>>(scheduleDTOS, HttpStatus.OK);
    }

    //Returns list of schedule of doctor work in particular hospital
    @GetMapping(value = "/hospitalDoctor/{doctorId}")
    public ResponseEntity<?> getScheduleByHospitalDoctorId(@PathVariable("doctorId") Long doctorId,
                                                           @RequestHeader String Authorization){
        Long hospitalId = sessionService.checkSession(Authorization).getHospitalId();
        List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleByHospitalDoctorId(hospitalId, doctorId);
        if(scheduleDTOS == null)
        {
            log.error("There is no schedule of doctor with doctorId: "+doctorId);
            return new ResponseEntity<String>("There is no schedule of doctor with doctorId: "+doctorId, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<ScheduleDTO>>(scheduleDTOS, HttpStatus.OK);
    }


    ///return list of hospitals with particular doctor schedule
    @GetMapping(value = "/hospitalSchedule/{doctorId}")
    public ResponseEntity<?> getHospitalsScheduleByDoctor(@PathVariable("doctorId") Long doctorId){
        Map<Long, List<ScheduleDTO>> stringListMap = new HashMap<>();
        stringListMap = scheduleService.getHospitals(doctorId);
        return new ResponseEntity< Map<Long, List<ScheduleDTO>>>(stringListMap, HttpStatus.OK);
    }

    //update schedule
    @PutMapping(value = "/{doctorId}")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleStringDTO scheduleStringDTO
                                            , @PathVariable("doctorId") Long doctorId
                                            , @RequestHeader String Authorization){
        System.out.println("*********************before *********************");
        System.out.println("        --          ");
        System.out.println(scheduleStringDTO.toString());
        System.out.println(Authorization);
        Long hospitalId = sessionService.checkSession(Authorization).getHospitalId();

        if(scheduleStringDTO.getEndTime().equals("") || scheduleStringDTO.getStartTime().equals("")){
            scheduleService.deleteSchedule(scheduleStringDTO.getId(),hospitalId,doctorId);
            return  new ResponseEntity<String>("Schedule Deleted", HttpStatus.OK);
        }

        ScheduleDTO scheduleDTO = scheduleService.convertIntoTime(scheduleStringDTO);
        System.out.println("************************After converting into Time*****************************");
        System.out.println(scheduleDTO.toString());

        if(scheduleDTO.getId() == null){
            ScheduleDTO scheduleDto= scheduleService.checkScheduleForInsert(scheduleDTO, doctorId);
            if(scheduleDto != null){
                log.error("The schedule is overLapped to "+scheduleDto);
                System.out.println("The schedule is overLapped to "+scheduleDto);
                return new ResponseEntity<ScheduleDTO>(scheduleDto,HttpStatus.OK);
            }
            else {
                ScheduleDTO catchSchedule = scheduleService.addSchedule(scheduleDTO, doctorId, hospitalId);
                log.info("Schedule has beed inserted sucessfully");
                return new ResponseEntity<ScheduleDTO>(catchSchedule, HttpStatus.OK);
            }



           /* log.error("Schedule id is null, can't update it.");
            return new ResponseEntity<String>("Schedule id is null, can't update it.", HttpStatus.CONFLICT);*/
        }

        ScheduleDTO scheduleDTO1 = scheduleService.checkScheduleForUpdate(scheduleDTO, doctorId);
        if(scheduleDTO1 != null  && (scheduleDTO1.getEndTime() != null || scheduleDTO1.getStartTime() != null) ){

            log.error("Schedule overLapped, Update schedule denied");
            return new ResponseEntity<ScheduleDTO>(scheduleDTO1, HttpStatus.OK);
        }

        ScheduleDTO catchSchedule = scheduleService.updateSchedule(scheduleDTO);
        if(catchSchedule == null){
            log.error("Update schedule unsuccessful");
            return new ResponseEntity<String>("Update schedule unsuccessful", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<ScheduleDTO>(catchSchedule, HttpStatus.OK);


    }



    }
