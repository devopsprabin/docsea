package org.itglance.docsea.repository;

import java.util.Date;
import java.util.List;

import org.itglance.docsea.domain.BloodPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by sanjib on 6/12/17.
 */
public interface BloodPostRepository extends JpaRepository <BloodPost,Long>{


    @Query(value = "SELECT b FROM BloodPost b WHERE b.deadline >= :date",
            countQuery = "SELECT count(b)FROM BloodPost b WHERE b.deadline >= :date")
    Page<BloodPost> findValideBlood( @Param("date") Date date, Pageable page );

    public BloodPost findByBloodGroup(String group);


    @Query("SELECT b FROM BloodPost b ORDER BY b.postDate desc ")
    public List<BloodPost> getBloodIndescending();

}
