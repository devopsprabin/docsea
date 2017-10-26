package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Days;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DaysRepository extends JpaRepository<Days,Long>{
    public Days findByDay(String days);




}