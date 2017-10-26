package org.itglance.docsea.domain;

import javax.persistence.*;

/**
 * Created by sanj__000 on 5/8/2017.
 */

@Entity
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String day;


    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Days{" +
                "id=" + id +
                ", day='" + day + '\'' +
                '}';
    }
}
