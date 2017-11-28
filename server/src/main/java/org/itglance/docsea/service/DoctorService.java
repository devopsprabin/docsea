package org.itglance.docsea.service;

import org.itglance.docsea.repository.SpecialityRepository;

import org.itglance.docsea.domain.*;
import org.itglance.docsea.repository.ContactRepository;
import org.itglance.docsea.repository.DoctorRepository;
import org.itglance.docsea.repository.ScheduleRepository;
import org.itglance.docsea.service.dto.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.itglance.docsea.repository.*;
import org.itglance.docsea.service.dto.ScheduleDTO;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by sriyanka on 5/20/2017.
 */

@Service
@Transactional
public class DoctorService {
    private final DoctorRepository doctorRepository ;
    private final SpecialityRepository specialityRepository;
    private final ContactRepository contactRepository;
    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;
    private final HospitalDoctorRepository hospitalDoctorRepository;
    private final HospitalRepository hospitalRepository;
    private final QualificationRepository qualificationRepository;


    @Autowired
    ScheduleService scheduleService;
    @Autowired
    StatusService statusService;
    @Autowired
    HttpServletRequest request;
    @Value("${DOCSEAPATH}")
    private String s;




    public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository
            , ContactRepository contactRepository, ScheduleRepository scheduleRepository
            , HospitalDoctorRepository hospitalDoctorRepository, HospitalRepository hospitalRepository
            , QualificationRepository qualificationRepository, SessionRepository sessionRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.contactRepository = contactRepository;
        this.scheduleRepository = scheduleRepository;
        this.sessionRepository = sessionRepository;
        this.hospitalDoctorRepository = hospitalDoctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.qualificationRepository=qualificationRepository;
    }


    public void addDoctor(DoctorDTO doctorDTO, String token) {
        Doctor doctor = new Doctor();
        System.out.println(token);
        Session session = sessionRepository.findByToken(token);
        Hospital hospital = hospitalRepository.findOne(session.getHospitalId());

        Doctor doctor1 =doctorRepository.findByNmcNumber(doctorDTO.getNmcNumber());
        System.out.println("===============doctorsss==================");
        System.out.println(doctor1);
        if(doctor1 == null) {

            doctor.setNmcNumber(doctorDTO.getNmcNumber());
            doctor.setName(doctorDTO.getName());
            doctor.setPhoto(doctorDTO.getPhoto());
            doctor.setGender(doctorDTO.getGender());

            Speciality speciality = new Speciality();
            speciality = specialityRepository.findByName(doctorDTO.getSpeciality().getName());
            doctor.setSpeciality(speciality);

            Qualification qualification = new Qualification();
            qualification = qualificationRepository.findByName(doctorDTO.getQualification().getName());
            doctor.setQualification(qualification);


            Contact contact = new Contact();
            contact.setContactNumber1(doctorDTO.getContact().getContactNumber1());
            contact.setContactNumber2(doctorDTO.getContact().getContactNumber2());
            contact.setEmailId(doctorDTO.getContact().getEmailId());
            contact.setWebsite(doctorDTO.getContact().getWebsite());
            contact.setFax(doctorDTO.getContact().getFax());
            contactRepository.save(contact);
            doctor.setContact(contact);

            doctor.setDetails(doctorDTO.getDetails());

            doctorRepository.save(doctor);
            System.out.println("--------------doctor added----------------");

            HospitalDoctor hospitalDoctor = new HospitalDoctor();

            hospitalDoctor.setHospital(hospital);
            hospitalDoctor.setDoctor(doctor);

            Status status = statusService.getStatusObject("ACTIVE");
            hospitalDoctor.setStatus(status);
            hospitalDoctorRepository.save(hospitalDoctor);
        }else{
            HospitalDoctor hospitalDoctor = new HospitalDoctor();

            hospitalDoctor.setHospital(hospital);
            hospitalDoctor.setDoctor(doctor1);

            Status status = statusService.getStatusObject("ACTIVE");
            hospitalDoctor.setStatus(status);
            hospitalDoctorRepository.save(hospitalDoctor);
        }


    }

    public boolean isDoctorExist(DoctorDTO doctorDTO, Long hospitalId){
        Doctor doctor=new Doctor();
        doctor=doctorRepository.findByNmcNumber(doctorDTO.getNmcNumber());
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        HospitalDoctor hospitalDoctor = hospitalDoctorRepository.findByHospitalAndDoctor(hospital,doctor);
        if(hospitalDoctor!=null ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isDoctorExist(Long id){

        Doctor doctor = doctorRepository.findOne(id);
        if(doctor!= null){
            return true;
        }
        return false;
    }

    public void updateDoctor(DoctorDTO doctorDTO){


        Doctor doctor = doctorRepository.findOne(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setGender(doctorDTO.getGender());
        doctor.setPhoto(doctorDTO.getPhoto());

        Contact contact = contactRepository.findOne(doctorDTO.getContact().getId());
        contact.setWebsite(doctorDTO.getContact().getWebsite());
        contact.setFax(doctorDTO.getContact().getFax());
        contact.setEmailId(doctorDTO.getContact().getEmailId());
        contact.setContactNumber1(doctorDTO.getContact().getContactNumber1());
        contact.setContactNumber2(doctorDTO.getContact().getContactNumber2());
        contactRepository.save(contact);
        doctor.setContact(contact);

        doctor.setNmcNumber(doctorDTO.getNmcNumber());
        doctor.setDetails(doctorDTO.getDetails());
        Qualification qualification=qualificationRepository.findByName(doctorDTO.getQualification().getName());
        doctor.setQualification(qualification);

        Speciality speciality=specialityRepository.findByName(doctorDTO.getSpeciality().getName());
        doctor.setSpeciality(speciality);

        doctorRepository.save(doctor);

    }

    public String renamePhoto(MultipartFile file) {
//        final String UPLOADED_FOLDER = "F:\\docsea\\docsea\\client\\src\\assets\\images\\";
//        final String UPLOADED_FOLDER = "F:\\college\\Project\\DocSea\\client\\src\\assets\\images\\";
//        final String UPLOADED_FOLDER = "media\\mahesh\\Local Disk\\mahesh\\workspace\\docsea\\client\\src\\assets\\images\\";
//        final String UPLOADED_FOLDER = "/media/sanjib/Work station/f/projects/DocSea/client/src/assets/images/";

        final String UPLOADED_FOLDER = s;
        System.out.println("*****************file upload path*********************");
        System.out.println(UPLOADED_FOLDER);
//        final String UPLOADED_FOLDER = "F:\\projects\\DocSea\\client\\src\\assets\\images\\";
        int random = (int) (Math.random() * 50000 + 1);
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        System.out.println(extension);
        String newFileName = "doctor-" + random + "." + extension;
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + newFileName);
            Files.write(path, bytes);
            System.out.println("***********Profile uploaded************");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    // Hospital nmc no. validation
    public boolean validateNmcforUpdate(DoctorDTO doctorDTO) {
        List<Doctor> doctors = new ArrayList<>();
        doctors = doctorRepository.findAllByNmcNumber(doctorDTO.getNmcNumber());
        if(doctors.size() == 0){
            return true;
        }else if(doctors.size() == 1){
            if(doctors.get(0).getId() == doctorDTO.getId()){
                return true;
            }
        }
        return false;
    }

        public void linkSchedule(Long id,ScheduleDTO scheduleDTO, List<Schedule> schedule){
            Doctor doctor=doctorRepository.getOne(id);
            schedule.add(scheduleRepository.findById(scheduleService.getScheduleId(scheduleDTO)));
            doctor.setSchedules(schedule);
            doctorRepository.save(doctor);
        }

    public List<DoctorDTO> getAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor d: doctors){
            doctorDTOS.add(new DoctorDTO(d));
        }
        return doctorDTOS;
    }

    public DoctorDTO getOneDoctor(Long id) {
        Doctor doctor = doctorRepository.findOne(id);
        return new DoctorDTO(doctor);
    }

    //added by urgyen for pagination
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }


}
