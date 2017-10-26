package org.itglance.docsea.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * Created by sriyanka on 5/8/2017.
 */


@Entity
public class Contact {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;
   
   @NotNull(message = "contact no.1 should not be null.")
   private String contactNumber1;

   private String contactNumber2;
   
   @Email
   @NotNull(message = "email Id should be not be null.")
   private String emailId;

   private String website;

   private String fax;

   public Long getId() {
      return id;
   }

   public void setId(long id){
      this.id = id;
   }

   public String getContactNumber1() {
      return contactNumber1;
   }

   public void setContactNumber1(String contactNumber1) {
      this.contactNumber1 = contactNumber1;
   }

   public String getContactNumber2() {
      return contactNumber2;
   }

   public void setContactNumber2(String contactNumber2) {
      this.contactNumber2 = contactNumber2;
   }

   public String getEmailId() {
      return emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public String getWebsite() {
      return website;
   }

   public void setWebsite(String website) {
      this.website = website;
   }

   public String getFax() {
      return fax;
   }

   public void setFax(String fax) {
      this.fax = fax;
   }

   @Override
   public String toString() {
      return "ContactDTO{" +
              "id=" + id +
              ", contactnumber1='" + contactNumber1 + '\'' +
              ", contactnumber2='" + contactNumber2 + '\'' +
              ", emailid='" + emailId + '\'' +
              ", website='" + website + '\'' +
              ", fax='" + fax + '\'' +
              '}';
   }
}
