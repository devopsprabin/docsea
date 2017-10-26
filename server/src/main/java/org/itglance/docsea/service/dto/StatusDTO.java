package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Status;

/**
 * Created by soni on 5/8/2017.
 */
public class StatusDTO {

    private Long id;
    private String status;

    public StatusDTO() {
    }

    public StatusDTO(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public StatusDTO(Status status){
        this(status.getId(),status.getStatus());
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
