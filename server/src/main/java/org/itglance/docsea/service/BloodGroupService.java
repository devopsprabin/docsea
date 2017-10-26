package org.itglance.docsea.service;

import org.itglance.docsea.domain.BloodGroup;
import org.itglance.docsea.repository.BloodGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanjib on 6/18/17.
 */

@Service
@Transactional
public class BloodGroupService {

    private final BloodGroupRepository bloodGroupRepository;

    public BloodGroupService(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public BloodGroup getBloodGroup(String bloodGroup){
        return bloodGroupRepository.findByBloodGroup(bloodGroup);
    }
}
