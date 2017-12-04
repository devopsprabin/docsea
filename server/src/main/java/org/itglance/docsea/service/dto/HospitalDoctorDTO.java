package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itglance.docsea.domain.Doctor;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.HospitalDoctor;
import org.itglance.docsea.domain.Status;

/**
 * Created by sriyanka on 5/25/2017.
 */
public class HospitalDoctorDTO {

    private Long id;
    private Hospital hospital;
    private Doctor doctor;

    @JsonIgnore
    private Status status;
    private String doc_status;

    public HospitalDoctorDTO(){

    }

    public HospitalDoctorDTO(Long id, Hospital hospital, Doctor doctor, Status status) {
        this.id = id;
        this.hospital = hospital;
        this.doctor = doctor;
        this.status = status;
        this.doc_status = status.getStatus();
    }

    public HospitalDoctorDTO(HospitalDoctor hospitalDoctor){
        this(hospitalDoctor.getId(),hospitalDoctor.getHospital(),hospitalDoctor.getDoctor(),hospitalDoctor.getStatus());
    }

    public Long getId() {
        return id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "HospitalDoctorDTO{" +
                "id=" + id +
                ", hospital=" + hospital +
                ", doctor=" + doctor +
                ", status=" + status +
                '}';
    }
}
