package org.itglance.docsea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by sanjib on 6/18/17.
 */


@Entity

public class BloodGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bloodGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    @Override
    public String toString() {
        return "BloodGroup{" +
                "id=" + id +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
