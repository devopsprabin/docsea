package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mahesh on 5/8/2017.
 */
public interface AddressRepository extends JpaRepository<Address,Long>{

    @Query("SELECT a.streetAddress FROM Address a WHERE a.streetAddress LIKE LOWER(CONCAT(:str, '%'))")
    public List<String> findThisDoctor(@Param("str") String str);
}
