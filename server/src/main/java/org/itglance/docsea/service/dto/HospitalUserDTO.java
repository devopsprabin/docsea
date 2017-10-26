package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.HospitalUser;
import org.itglance.docsea.domain.User;

/**
 * Created by sanj__000 on 5/13/2017.
 */
public class HospitalUserDTO {

    private Long id;
    private Hospital hospital;
    private User user;

    public HospitalUserDTO() {
    }

    public HospitalUserDTO(Long id, Hospital hospital, User user) {
        this.id = id;
        this.hospital = hospital;
        this.user = user;
    }

    public HospitalUserDTO(HospitalUser hospitalUser) {
        this.id = hospitalUser.getId();
        this.hospital = hospitalUser.getHospital();
        this.user = hospitalUser.getUser();
    }

    public Long getId() {
        return id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "HospitalUserDTO{" +
                "id=" + id +
                ", hospital=" + hospital +
                ", user=" + user +
                '}';
    }
}
