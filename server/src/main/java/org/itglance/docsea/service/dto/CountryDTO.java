package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Country;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class CountryDTO {
    private Long id;
    private String name;

    public CountryDTO() {
    }

    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryDTO(Country country){
        this(country.getId(),country.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
