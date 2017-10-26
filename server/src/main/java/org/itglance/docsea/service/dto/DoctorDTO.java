package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriyanka on 5/8/2017.
 */
public class DoctorDTO {

    private Long id;
    private int nmcNumber;
    private String name;
    private Qualification qualification;
    private String photo;
    private String gender;
    private Speciality speciality;
    private Contact contact;
    private List<Schedule> schedules=new ArrayList<>();
    private String details;

    public DoctorDTO(){}

    public DoctorDTO(Long id, int nmcNumber, String name, Qualification qualification, String photo,
                String gender, Speciality speciality, Contact contact, List<Schedule> schedules, String details) {
        this.id = id;
        this.nmcNumber = nmcNumber;
        this.name = name;
        this.qualification = qualification;
        this.photo = photo;
        this.gender = gender;
        this.speciality = speciality;
        this.contact = contact;
        this.schedules = schedules;
        this.details = details;
    }

    public DoctorDTO(Doctor doctor){
        this(doctor.getId(),doctor.getNmcNumber(),doctor.getName(),doctor.getQualification(),doctor.getPhoto(),doctor.getGender(),doctor.getSpeciality(),doctor.getContact(), doctor.getSchedules(),doctor.getDetails());

    }

    public Long getId() {
        return id;
    }


    public int getNmcNumber(){ return nmcNumber;}

    public String getName() { return name;}


    public Qualification getQualification() {
        return qualification;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender(){ return gender; }

    public Speciality getSpeciality() {
        return speciality;
    }

    public Contact getContact() {
        return contact;
    }

    public String getDetails() {
        return details;
    }


    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id=" + id +
                ", nmcNumber=" + nmcNumber +
                ", name='" + name + '\'' +
                ", qualification='" + qualification + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", speciality=" + speciality +
                ", contact=" + contact +
                ", details='" + details + '\'' +
                '}';
    }

}

