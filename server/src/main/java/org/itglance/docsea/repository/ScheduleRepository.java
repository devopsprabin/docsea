package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>{

    @Query("select s from Schedule s where s.startTime like :startTime AND s.endTime like :endTime AND s.days.id=:days")
    public Schedule findByStartEndTime(@Param("startTime") Time startTime, @Param("endTime") Time endTime, @Param("days") Long days );

    public Schedule findById(Long id);


   /* @Query("select s from Schedule s where s.startTime like :startTime AND s.endTime like :endTime AND s.days.day like :days" +
            "AND ")
    Schedule findByStartEndTimeDocId(Time startTime, Time endTime, String day, Long doctorId);*/
}