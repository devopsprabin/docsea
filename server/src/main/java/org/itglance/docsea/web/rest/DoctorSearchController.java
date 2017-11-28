package org.itglance.docsea.web.rest;

import org.itglance.docsea.service.DoctorSearchService;
import org.itglance.docsea.service.dto.DoctorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriyanka on 5/25/2017.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api/doctorSearch")
public class DoctorSearchController {

    @Autowired
    private DoctorSearchService doctorSearchService;

    private static final Logger logger= LoggerFactory.getLogger(DoctorSearchController.class);

    @RequestMapping(value="/{searchString}", method = RequestMethod.GET)
    public ResponseEntity<List<DoctorDTO>> searchDoctor(@PathVariable String searchString){

        logger.info("********************search string***********************");
        List<DoctorDTO> doctors= doctorSearchService.findDoctor(searchString);
        System.out.println(doctors);
        if(doctors.isEmpty()){
            System.out.println("*********************doctor not found********************");
            return  new ResponseEntity("Doctor not found", HttpStatus.CONFLICT);

        }
        logger.info(searchString);
        return new ResponseEntity<>(doctors,HttpStatus.OK);
    }

    //Search all the doctor according to the hospital
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> searchAllDoctorOfHospital(@RequestHeader String Authorization){
        List<DoctorDTO> doctorDTOS = doctorSearchService.findAllDoctorsOfHospital(Authorization);
        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/quickSearch/{str}")
    public ResponseEntity<List<String>> quickSearch(@PathVariable("str") String str){
        List<String> searchResults = doctorSearchService.getStringListForSearch(str);
        logger.info("------search result------");
       return new ResponseEntity<>(searchResults, HttpStatus.OK);

    }
}
