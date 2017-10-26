package org.itglance.docsea.web.rest;

import org.itglance.docsea.repository.SpecialityRepository;
import org.itglance.docsea.service.SpecialityService;
import org.itglance.docsea.service.dto.SpecialityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by soni on 5/30/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/api/speciality")
public class SpecialityController {

    SpecialityRepository specialityRepository;

    public SpecialityController(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Autowired
    SpecialityService specialityService;

    @GetMapping
    public ResponseEntity<?> getAllSpeciality(){
        List<SpecialityDTO> specialities = specialityService.getAllSpeciality();
        return new ResponseEntity<List<SpecialityDTO>>(specialities, HttpStatus.OK);
    }
}
