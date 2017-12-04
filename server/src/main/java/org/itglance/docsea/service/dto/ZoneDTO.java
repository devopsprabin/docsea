package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itglance.docsea.domain.Country;
import org.itglance.docsea.domain.Zone;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class ZoneDTO {
    private Long id;
    private String name;

    @JsonIgnore
    private Country country;
    private String country_name;

    public ZoneDTO() {
    }

    public ZoneDTO(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.country_name = country.getName();
    }

    public ZoneDTO(Zone zone){
        this(zone.getId(),zone.getName(),zone.getCountry());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public String getCountry_name() {
        return country_name;
    }

    @Override
    public String toString() {
        return "ZoneDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
