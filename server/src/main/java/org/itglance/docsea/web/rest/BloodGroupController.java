package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.BloodGroup;
import org.itglance.docsea.repository.BloodGroupRepository;
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

    public BloodGroupController(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    @GetMapping
    public ResponseEntity<?> getBloodGroup(){
        return new ResponseEntity<List<BloodGroup>>(bloodGroupRepository.findAll(), HttpStatus.OK);
    }
}
