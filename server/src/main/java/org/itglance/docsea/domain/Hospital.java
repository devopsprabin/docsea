package org.itglance.docsea.domain;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanj__000 on 5/8/2017.
 */

@Entity
public class Hospital {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Hospital name shouldn't be null")
    private String name;
    
    @NotNull(message = "Hospital Lisence no. shouldn't be null")
    private String lisenceNo;

    @NotNull(message = "Hospital registration no. shouldn't be null")
    private String registrationNo;

    @NotNull(message = "Hospital's contact should not be null (foreign key)")
    @OneToOne
    private Contact contact;
    
    @NotNull(message = "Hospital's address should not be null (foreign key)")
    @OneToOne
    private Address address;

    @ManyToMany
    private List<Schedule> schedules=new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLisenceNo() {
        return lisenceNo;
    }

    public void setLisenceNo(String lisenceNo) {
        this.lisenceNo = lisenceNo;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lisenceNo='" + lisenceNo + '\'' +
                ", registrationNo='" + registrationNo + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                ", schedules=" + schedules +
                '}';
    }
}
