package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Days;
import org.itglance.docsea.domain.Schedule;

import java.sql.Time;

/**
 * Created by sanj__000 on 5/30/2017.
 */
public class ScheduleStringDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private Days days;


    public ScheduleStringDTO() {
    }


    public ScheduleStringDTO(Long id, String startTime, String endTime, Days days) {

        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public Days getDays() {
        return days;}


    @Override
    public String toString() {
        return "ScheduleStringDTO{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", days=" + days +
                '}';
    }
}
