//<<<<<<< HEAD
//package org.itglance.docsea.domain;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * Created by Mahesh on 5/9/2017.
// */
//@Entity
//public class Event {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String eventName;
//    private String description;
//    private String dates;
//    private String time;
//    private String photo;
//
//    @JoinColumn(name="hospital_id")
//    @ManyToOne
//    private Hospital hospital;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getEventName() {
//        return eventName;
//    }
//
//    public void setEventName(String eventName) {
//        this.eventName = eventName;
//    }
//
//    public Hospital getHospital() {
//        return hospital;
//    }
//
//    public void setHospital(Hospital hospital) {
//        this.hospital = hospital;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDates() {
//        return dates;
//    }
//
//    public void setDates(String dates) {
//        this.dates = dates;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
//
//    @Override
//    public String toString() {
//        return "Event{" +
//                "id=" + id +
//                ", eventName='" + eventName + '\'' +
//                ", hospital=" + hospital +
//                ", description='" + description + '\'' +
//                ", dates='" + dates + '\'' +
//                ", time='" + time + '\'' +
//                ", photo='" + photo + '\'' +
//                '}';
//    }
//}
//=======
package org.itglance.docsea.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sriyanka on 6/12/17.
 */

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Hospital hospital;

    @Type(type = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dates;

    private String time;
    private String photo;
    private String location;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event{"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", hospital=" + hospital +
                ", description='" + description + '\'' +
                ", dates=" + dates +
                ", time='" + time + '\'' +
                ", photo='" + photo + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
