package org.itglance.docsea.service;

import org.itglance.docsea.domain.Status;
import org.itglance.docsea.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.itglance.docsea.domain.*;
import org.itglance.docsea.repository.*;
import org.itglance.docsea.service.dto.HospitalDoctorDTO;
import org.itglance.docsea.service.dto.HospitalUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by soni on 5/8/2017.
 */
@Service
@Transactional
public class StatusService {

    @Autowired
    SessionService sessionService;

    private final Logger log = LoggerFactory.getLogger(Status.class);
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final HospitalDoctorRepository hospitalDoctorRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalUserRepository hospitalUserRepository;

    public StatusService(StatusRepository statusRepository, UserRepository userRepository, HospitalRepository hospitalRepository, HospitalDoctorRepository hospitalDoctorRepository
            , DoctorRepository doctorRepository, HospitalUserRepository hospitalUserRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.hospitalRepository = hospitalRepository;
        this.hospitalDoctorRepository = hospitalDoctorRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalUserRepository = hospitalUserRepository;
    }

    public void addStatus(String statusStr){
        Status status = new Status();
        status.setStatus(statusStr);
        statusRepository.save(status);
        log.info("New Status "+statusStr+" has been inserted into Status table");

    }

    public Status getStatusObject(String statusStr)
    {
        Status status= new Status();
        List<Status> statusList= statusRepository.findAll();
        if(!statusList.isEmpty()){
            for(Status s : statusList){
                if(s.getStatus().equalsIgnoreCase(statusStr)){
                    return s;
                }
            }
        }
        addStatus(statusStr);
        status = statusRepository.findByStatus(statusStr);
        return status;
    }

    public HospitalUserDTO toggleHospitalStatus(Long userId) {
        HospitalUser hospitalUser = hospitalUserRepository.findOne(userId);
        User user = userRepository.findOne(hospitalUser.getUser().getId());

        Status status = new Status();
        if(user.getStatus().getStatus().equalsIgnoreCase("ACTIVE"))
        {
            status = getStatusObject("INACTIVE");
            System.out.println("Username "+user.getUsername()+" updated to INACTIVE");
        }else if(user.getStatus().getStatus().equalsIgnoreCase("INACTIVE")){
            status = getStatusObject("ACTIVE");
            System.out.println("Username "+user.getUsername()+" updated to ACTIVE");
        }else{
            status = getStatusObject("DEACTIVE");
            System.out.println("Username "+user.getUsername()+" user is DEACTIVE");
        }
        user.setStatus(status);
        userRepository.save(user);
        HospitalUser hospitalUser1 = hospitalUserRepository.findOne(userId);
        return new HospitalUserDTO(hospitalUser1);
    }

    public HospitalDoctorDTO toggleDoctorStatus(Long docotorId, String authorization) {

        Session session = sessionService.checkSession(authorization);
        Hospital hospital = hospitalRepository.findOne(session.getHospitalId());
        Doctor doctor = doctorRepository.findOne(docotorId);
        HospitalDoctor hospitalDoctor = hospitalDoctorRepository.findByHospitalAndDoctor(hospital, doctor);
        Status status = new Status();
        if(hospitalDoctor.getStatus().getStatus().equalsIgnoreCase("ACTIVE"))
        {
            status = getStatusObject("INACTIVE");
            System.out.println("Doctor status whose name is "+doctor.getName()+", updated to INACTIVE");
        }else if(hospitalDoctor.getStatus().getStatus().equalsIgnoreCase("INACTIVE")){
            status = getStatusObject("ACTIVE");
            System.out.println("Doctor status whose name is "+doctor.getName()+",updated to ACTIVE");
        }else{
            status = getStatusObject("DEACTIVE");
            System.out.println("Doctor whose name is "+doctor.getName()+" is deactivated");
        }
        hospitalDoctor.setStatus(status);
        hospitalDoctorRepository.save(hospitalDoctor);
        HospitalDoctor hospitalDoctor1 = hospitalDoctorRepository.findByHospitalAndDoctor(hospital, doctor);
        return new HospitalDoctorDTO(hospitalDoctor1);
    }
}
