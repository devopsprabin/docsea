package org.itglance.docsea.repository;

import java.util.List;

import org.itglance.docsea.domain.District;
import org.itglance.docsea.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by sanj__000 on 5/20/2017.
 */
public interface DistrictRepository extends JpaRepository<District, Long> {
    public List<District> findAllByZone(Zone zone);
    public District findByName(String district);

    @Query("SELECT d.name FROM District d WHERE d.name LIKE LOWER(CONCAT(:str, '%'))")
    public List<String> findThisDoctor(@Param("str") String str);
}
