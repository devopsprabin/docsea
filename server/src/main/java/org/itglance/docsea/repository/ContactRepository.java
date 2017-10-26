package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanj__000 on 5/12/2017.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    public Contact findByContactNumber1(String num);
}
