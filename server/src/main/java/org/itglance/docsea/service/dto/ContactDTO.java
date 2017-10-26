package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Contact;

/**
 * Created by sriyanka on 5/8/2017.
 */
public class ContactDTO {
    private Long id;
    private String contactNumber1;
    private String contactNumber2;
    private String emailId;
    private String website;
    private String fax;

    public ContactDTO(){

    }

    public ContactDTO(Long id, String contactNumber1, String contactNumber2, String emailId, String website, String fax) {
        this.id = id;
        this.contactNumber1 = contactNumber1;
        this.contactNumber2 = contactNumber2;
        this.emailId = emailId;
        this.website = website;
        this.fax = fax;
    }

    public ContactDTO(Contact contact){
        this(contact.getId(),contact.getContactNumber1(),contact.getContactNumber2(),contact.getEmailId(),contact.getWebsite(),contact.getFax());
    }



    public Long getId() {
        return id;
    }

    public String getContactNumber1() {
        return contactNumber1;
    }

    public String getContactNumber2() {
        return contactNumber2;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getWebsite() {
        return website;
    }

    public String getFax() {
        return fax;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", contactNumber1='" + contactNumber1 + '\'' +
                ", contactNumber2='" + contactNumber2 + '\'' +
                ", emailId='" + emailId + '\'' +
                ", website='" + website + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
