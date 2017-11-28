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
    public ResponseEntity<?> searchDoctor(@PathVariable String searchString){
        logger.info("********************search string***********************");
        logger.info(searchString);
       List<DoctorDTO> doctors= doctorSearchService.findDoctor(searchString);
        System.out.println(doctors);
        if(doctors.isEmpty()){
            logger.error("*********************doctor not found********************");
            return  new ResponseEntity<String>("Doctor not found", HttpStatus.CONFLICT);
        }
       return new ResponseEntity<List<DoctorDTO>>(doctors,HttpStatus.OK);
    }

    //Search all the doctor according to the hospital
    @GetMapping
    public ResponseEntity<?> searchAllDoctorOfHospital(@RequestHeader String Authorization){
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        doctorDTOS = doctorSearchService.findAllDoctorsOfHospital(Authorization);
        return new ResponseEntity<List<DoctorDTO>>(doctorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/quickSearch/{str}")
    public ResponseEntity<?> quickSearch(@PathVariable("str") String str){
        List<String> searchResults =new ArrayList<>();
        searchResults = doctorSearchService.getStringListForSearch(str);
        logger.info("------search result------");
       return new ResponseEntity<List<String>>(searchResults, HttpStatus.OK);
    }
}
