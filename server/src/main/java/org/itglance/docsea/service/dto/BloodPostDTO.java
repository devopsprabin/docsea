package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.BloodGroup;
import org.itglance.docsea.domain.BloodPost;
import org.itglance.docsea.domain.Contact;


import java.util.Date;

/**
 * Created by sanjib on 6/12/17.
 */
public class BloodPostDTO {

    private Long id;

    private String post;

    private BloodGroup bloodGroup;

    private Date deadline;

    private Date postDate;

    private String contact;

    private String location;

    public BloodPostDTO() {
    }

    public BloodPostDTO(Long id, String post, BloodGroup bloodGroup, Date deadline, Date postDate, String contact, String location) {
        this.id = id;
        this.post = post;
        this.bloodGroup = bloodGroup;
        this.deadline = deadline;
        this.postDate = postDate;
        this.contact = contact;
        this.location = location;
    }

    public BloodPostDTO(BloodPost bloodPost){
        this(bloodPost.getId(),bloodPost.getPost(), bloodPost.getBloodGroup(), bloodPost.getDeadline(), bloodPost.getPostDate(), bloodPost.getContact(), bloodPost.getLocation());
    }

    public Long getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getContact() {
        return contact;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "BloodPostDTO{" +
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
