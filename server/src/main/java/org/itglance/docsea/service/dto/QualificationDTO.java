package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Qualification;

/**
 * Created by soni on 5/30/2017.
 */
public class QualificationDTO {

    private Long id;
    private String name;

    public QualificationDTO() {
    }

    public QualificationDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public QualificationDTO(Qualification name){
        this(name.getId(), name.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
