package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itglance.docsea.domain.District;
import org.itglance.docsea.domain.Zone;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class DistrictDTO {
    private Long id;
    private String name;

    @JsonIgnore
    private Zone zone;
    private String zone_name;

    public DistrictDTO() {
    }

    public DistrictDTO(Long id, String name, Zone zone) {
        this.id = id;
        this.name = name;
        this.zone = zone;
        this.zone_name = zone.getName();
    }

    public DistrictDTO(District district){
        this(district.getId(),district.getName(),district.getZone());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Zone getZone() {
        return zone;
    }

    public String getZone_name() {
        return zone_name;
    }

    @Override
    public String toString() {
        return "DistrictDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zone=" + zone +
                '}';
    }
}
