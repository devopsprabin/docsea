package org.itglance.docsea.service;

import org.itglance.docsea.domain.Qualification;
import org.itglance.docsea.repository.QualificationRepository;
import org.itglance.docsea.service.dto.QualificationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanj__000 on 6/7/2017.
 */
@Service
@Transactional
public class QualificationService {
    private final QualificationRepository qualificationRepository;

    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    public List<QualificationDTO> getAllQualification() {
        List<Qualification> allQualification = qualificationRepository.findAll();
        List<QualificationDTO> qualificationDTOS = new ArrayList<>();
        for(Qualification q: allQualification){
            qualificationDTOS.add(new QualificationDTO(q));
        }
        return qualificationDTOS;
    }
}
