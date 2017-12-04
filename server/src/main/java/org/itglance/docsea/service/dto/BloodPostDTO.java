package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itglance.docsea.domain.BloodGroup;
import org.itglance.docsea.domain.BloodPost;


import java.util.Date;

/**
 * Created by sanjib on 6/12/17.
 */

public class BloodPostDTO {

    private Long id;

    private String post;

    @JsonIgnore
    private BloodGroup group;

    private String bloodGroup;

    private Date deadline;

    @JsonIgnore
    private Date postDate;

    private String contact;

    private String location;

    public BloodPostDTO() {
    }

    public BloodPostDTO(Long id, String post, BloodGroup group, Date deadline, Date postDate, String contact, String location) {
        this.id = id;
        this.post = post;
        this.group = group;
        this.deadline = deadline;
        this.postDate = postDate;
        this.contact = contact;
        this.location = location;
        this.bloodGroup = group.getBloodGroup();
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

    public BloodGroup getGroup() {
        return group;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    @Override
    public String toString() {
        return "BloodPostDTO{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", group=" + group +
                ", deadline=" + deadline +
                ", postDate=" + postDate +
                ", contact='" + contact + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
