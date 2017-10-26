package org.itglance.docsea.service;

import org.itglance.docsea.domain.Speciality;
import org.itglance.docsea.repository.SpecialityRepository;
import org.itglance.docsea.service.dto.SpecialityDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanj__000 on 6/7/2017.
 */
@Service
@Transactional
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<SpecialityDTO> getAllSpeciality() {
        List<Speciality> allSpeciality = specialityRepository.findAll();
        List<SpecialityDTO> specialityDTOS = new ArrayList<>();
        for(Speciality s: allSpeciality){
            specialityDTOS.add(new SpecialityDTO(s));
        }
        return specialityDTOS;
    }
}
