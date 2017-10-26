package org.itglance.docsea.service;

import java.util.ArrayList;
import java.util.List;

import org.itglance.docsea.domain.Doctor;
import org.itglance.docsea.domain.DoctorStatus;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.Status;
import org.itglance.docsea.repository.DoctorRepository;
import org.itglance.docsea.repository.HospitalDoctorRepository;
import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.repository.ScheduleRepository;
import org.itglance.docsea.service.dto.DoctorDTO;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.StatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sriyanka on 5/23/2017.
 */

@Service
@Transactional
public class HospitalDoctorService {

    public static final Logger logger = LoggerFactory.getLogger(HospitalDoctorService.class);

    private final HospitalDoctorRepository hospitalDoctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository  doctorRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    StatusService statusService;
    @Autowired
    SessionService sessionService;

    public HospitalDoctorService(HospitalDoctorRepository hospitalDoctorRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository, ScheduleRepository scheduleRepository) {
        this.hospitalDoctorRepository = hospitalDoctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public StatusDTO getStatusFromHospitalAndDoctor(Long doctorId, String token) {
        Long hospitalId = sessionService.checkSession(token).getHospitalId();
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Doctor doctor = doctorRepository.findOne(doctorId);
        StatusDTO status = new StatusDTO(hospitalDoctorRepository.findStatusByHospitalDoctor(hospital, doctor));
        return status;
    }

    public List<HospitalDTO> getHospitals(Long docId) {
        Doctor doctor = doctorRepository.findOne(docId);
        Status status = statusService.getStatusObject("Active");
        List<Hospital> hospitals= hospitalDoctorRepository.findAllByDoctor(doctor, status);
        List<HospitalDTO> hospitalDTOS = new ArrayList<>();
        for(Hospital h: hospitals){
            hospitalDTOS.add(new HospitalDTO(h));
        }
        return hospitalDTOS;
    }

    public List<DoctorDTO> getDoctors(Long hospitalId) {
        Hospital hospital=hospitalRepository.findOne(hospitalId);
        List<Doctor> doctors=hospitalDoctorRepository.findAllByHospital(hospital);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor d: doctors){
            doctorDTOS.add(new DoctorDTO(d));
        }
        return doctorDTOS;
    }

    public List<DoctorStatus> getDoctorStatus(Long hospitalId, String authorization) {
        Hospital hospital=hospitalRepository.findOne(hospitalId);
        List<Doctor> doctors=hospitalDoctorRepository.findAllByHospital(hospital);
        List<DoctorStatus> doctoStatus = new ArrayList<>();
        for(Doctor d: doctors){
            DoctorStatus doctorStatus = new DoctorStatus();
            doctorStatus.setDoctor(d);
            Status s = new Status();
            s.setStatus(getStatusFromHospitalAndDoctor(d.getId(),authorization).getStatus());
            doctorStatus.setStatus(s);
            doctoStatus.add(doctorStatus);
        }
        System.out.println("********doctor staus************");

        System.out.print(doctoStatus);
        return doctoStatus;
    }
}
