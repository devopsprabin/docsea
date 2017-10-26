package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Country;
import org.itglance.docsea.domain.Zone;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class ZoneDTO {
    private Long id;
    private String name;
    private Country country;

    public ZoneDTO() {
    }

    public ZoneDTO(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    @Override
    public String toString() {
        return "ZoneDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
