package org.itglance.docsea.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

/**
 * Created by sanjib on 6/12/17.
 */

@Entity
public class BloodPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "text")
    private String post;

    @ManyToOne
    private BloodGroup bloodGroup;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    @Temporal(TemporalType.DATE)
    private Date postDate;

    private String contact;

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "BloodPost{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", bloodGroup=" + bloodGroup +
                ", deadline=" + deadline +
                ", postDate=" + postDate +
                ", contact='" + contact + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
