package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.BloodGroup;
import org.itglance.docsea.repository.BloodGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sonika on 6/20/17.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api/bloodGroup")
public class BloodGroupController {

    private BloodGroupRepository bloodGroupRepository;
    public static final Logger logger = LoggerFactory.getLogger(BloodGroupController.class);


    public BloodGroupController(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    @GetMapping
    public ResponseEntity<List<BloodGroup>> getBloodGroup(){
        logger.info("GET blood group Info api called  ");

        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        if(bloodGroupList == null){
            logger.error("There is no records in city table.");
            return new ResponseEntity(("There is no records in city table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bloodGroupList, HttpStatus.OK);
    }
}
