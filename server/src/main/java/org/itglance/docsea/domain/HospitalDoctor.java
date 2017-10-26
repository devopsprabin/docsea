package org.itglance.docsea.domain;

import javax.persistence.*;

/**
 * Created by soni on 5/9/2017.
 */
@Entity
public class HospitalDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id=id; }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HospitalDoctor{" +
                "id=" + id +
                ", hospital=" + hospital +
                ", doctor=" + doctor +
                ", status=" + status +
                '}';
    }
}
