package org.itglance.docsea.repository;

import org.itglance.docsea.domain.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanjib on 6/18/17.
 */
public interface BloodGroupRepository extends JpaRepository <BloodGroup, Long>{
    public BloodGroup findByBloodGroup(String bloodGroup);
}
