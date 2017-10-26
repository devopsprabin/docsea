package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.DoctorStatus;
import org.itglance.docsea.service.HospitalDoctorService;
import org.itglance.docsea.service.SessionService;
import org.itglance.docsea.service.dto.DoctorDTO;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sriyanka on 5/23/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/hospitalDoctor")
public class HospitalDoctorController {

    @Autowired
    private HospitalDoctorService hospitalDoctorService;

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/status/{doctorId}")
    public ResponseEntity<?> getStatusOfDoctor(@RequestHeader String Authorization, @PathVariable("doctorId") Long doctorId){

        StatusDTO statusDTO = hospitalDoctorService.getStatusFromHospitalAndDoctor(doctorId, Authorization);
        System.out.println("**************status of doctor**********");
        System.out.println("doctor status having id:"+doctorId+" : "+statusDTO.getStatus());
        return new ResponseEntity<StatusDTO>(statusDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{docId}")
    public ResponseEntity<?> gethospitals(@PathVariable("docId") Long docId){
        List<HospitalDTO> hospitalDTOS = hospitalDoctorService.getHospitals(docId);
        return  new ResponseEntity<List<HospitalDTO>>(hospitalDTOS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getHospitalDoctors(@RequestHeader String Authorization){
        Long hospitalId=sessionService.checkSession(Authorization).getHospitalId();
        List<DoctorDTO> doctors=hospitalDoctorService.getDoctors(hospitalId);
        if(doctors == null){
            return new ResponseEntity<String>("There is no doctor registered in your hospital",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DoctorDTO>>(doctors,HttpStatus.OK);
    }

    @GetMapping(value = "/doctorListWithStatus")
    public ResponseEntity<?> getHospitalDoctorsWithStatus(@RequestHeader String Authorization){
        Long hospitalId=sessionService.checkSession(Authorization).getHospitalId();
        List<DoctorStatus> doctors=hospitalDoctorService.getDoctorStatus(hospitalId,Authorization);
        if(doctors == null){
            return new ResponseEntity<String>("There is no doctor registered in your hospital",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DoctorStatus>>(doctors,HttpStatus.OK);
    }
}
