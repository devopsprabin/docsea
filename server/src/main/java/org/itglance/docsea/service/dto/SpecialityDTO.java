package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Speciality;

/**
 * Created by sriyanka on 5/8/2017.
 */
public class SpecialityDTO {
    private Long id;
    private String name;

    public SpecialityDTO(){

    }

    public SpecialityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpecialityDTO(Speciality speciality){
        this(speciality.getId(),speciality.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SpecialityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
