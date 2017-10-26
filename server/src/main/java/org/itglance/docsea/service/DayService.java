package org.itglance.docsea.service;

import org.itglance.docsea.domain.Days;
import org.itglance.docsea.repository.DaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanj__000 on 5/25/2017.
 */


@Service
@Transactional
public class DayService {
    private final Logger log = LoggerFactory.getLogger(DayService.class);

    private final DaysRepository daysRepository;

    public DayService(DaysRepository daysRepository) {
        this.daysRepository = daysRepository;
    }

    public Days getDayObject(String day){
        Days days = daysRepository.findByDay(day);

        if(days == null){
            System.out.println("***** Day **** "+day);
            Days d = new Days();
            d.setDay(day);
            daysRepository.save(d);
            return  d;
        }
        return days;
    }
}
