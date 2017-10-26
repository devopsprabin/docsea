package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.BloodGroup;

/**
 * Created by sanjib on 6/18/17.
 */
public class BloodGroupDTO {

    private Long id;

    private String bloodGroup;


    public BloodGroupDTO() {
    }

    public BloodGroupDTO(Long id, String bloodGroup) {
        this.id = id;
        this.bloodGroup = bloodGroup;
    }

    public BloodGroupDTO(BloodGroup bloodGroup) {
        this(bloodGroup.getId(),bloodGroup.getBloodGroup());
    }


    public Long getId() {
        return id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    @Override
    public String toString() {
        return "BloodGroupDTO{" +
                "id=" + id +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
