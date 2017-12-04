package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itglance.docsea.domain.City;
import org.itglance.docsea.domain.District;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class CityDTO {
    private Long id;
    private String name;

    @JsonIgnore
    private District district;
    private String district_name;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, District district) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.district_name = district.getName();
    }

    public CityDTO(City city) {
        this(city.getId(),city.getName(),city.getDistrict());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    public String getDistrict_name() {
        return district_name;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", district=" + district +
                '}';
    }
}
