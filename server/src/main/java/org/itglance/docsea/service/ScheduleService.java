package org.itglance.docsea.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itglance.docsea.domain.Days;
import org.itglance.docsea.domain.Doctor;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.Schedule;
import org.itglance.docsea.domain.Status;
import org.itglance.docsea.repository.DaysRepository;
import org.itglance.docsea.repository.DoctorRepository;
import org.itglance.docsea.repository.HospitalDoctorRepository;
import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.repository.ScheduleRepository;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.ScheduleDTO;
import org.itglance.docsea.service.dto.ScheduleStringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sriyanka on 5/21/2017.
 */

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DaysRepository daysRepository;
    private  final HospitalRepository hospitalRepository;
    private  final DoctorRepository doctorRepository;
    private final HospitalDoctorRepository hospitalDoctorRepository;


    @Autowired
    public StatusService statusService;

    @Autowired
    public DayService dayService;

    public ScheduleService(ScheduleRepository scheduleRepository, DaysRepository daysRepository,
                           HospitalRepository hospitalRepository
                            ,DoctorRepository doctorRepository, HospitalDoctorRepository hospitalDoctorRepository) {
        this.scheduleRepository = scheduleRepository;
        this.daysRepository = daysRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalDoctorRepository = hospitalDoctorRepository;

    }

    //inserting Schedule
    public ScheduleDTO addSchedule(ScheduleDTO scheduleDTO, Long doctorId, long hospitalId){

        Schedule schedule = new Schedule();
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());

        Days days = dayService.getDayObject(scheduleDTO.getDays().getDay());
        schedule.setDays(days);
        scheduleRepository.save(schedule);

        Hospital hospital = hospitalRepository.findOne(hospitalId);
        hospital.getSchedules().add(schedule);
        hospitalRepository.save(hospital);

        Doctor doctor = doctorRepository.findOne(doctorId);
        doctor.getSchedules().add(schedule);
        doctorRepository.save(doctor);

        return new ScheduleDTO(schedule);
    }

    public List<ScheduleDTO> getSchedule(){
         List<Schedule> sceScheduleDTOS= scheduleRepository.findAll();
         List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
         for(Schedule s : sceScheduleDTOS){
             scheduleDTOS .add(new ScheduleDTO(s));
         }
         return scheduleDTOS;
    }

    public ScheduleDTO checkScheduleForInsert(ScheduleDTO scheduleDTO, Long doctorId){
        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Schedule> schedules = doctor.getSchedules() ;
        for(Schedule s : schedules){

            if(s.getStartTime().equals(scheduleDTO.getStartTime()) ||
                    ((s.getStartTime().before(scheduleDTO.getStartTime()) && s.getEndTime().after(scheduleDTO.getStartTime()))) ||
                    s.getEndTime().equals(scheduleDTO.getEndTime()) ||
                    ((s.getStartTime().before(scheduleDTO.getEndTime()) && s.getEndTime().after(scheduleDTO.getEndTime())))
                    ){
                        if(s.getDays().getDay() . equals(scheduleDTO.getDays().getDay())){
                            return new ScheduleDTO(s);
                        }
            }
        }

        return null;
    }

    public ScheduleDTO checkScheduleForUpdate(ScheduleDTO scheduleDTO, Long doctorId){
        Schedule schedule = scheduleRepository.findOne(scheduleDTO.getId());
        if(new ScheduleDTO(schedule )== scheduleDTO){
            return null;
        }
        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Schedule> schedules = doctor.getSchedules() ;
        for(Schedule s : schedules){
            if(s.getStartTime().equals(scheduleDTO.getStartTime()) ||
                    ((s.getStartTime().before(scheduleDTO.getStartTime()) && s.getEndTime().after(scheduleDTO.getStartTime()))) ||
                    s.getEndTime().equals(scheduleDTO.getEndTime()) ||
                    ((s.getStartTime().before(scheduleDTO.getEndTime()) && s.getEndTime().after(scheduleDTO.getEndTime())))
                    ){
                if(s.getDays().getDay() . equals(scheduleDTO.getDays().getDay())){
                    if(scheduleDTO.getId() == s.getId()){
                        return null;
                    }
                    return new ScheduleDTO(s);
                }
            }
        }

        return null;
    }




    public  Long getScheduleId(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findByStartEndTime(scheduleDTO.getStartTime(), scheduleDTO.getEndTime(), scheduleDTO.getDays().getId());
        System.out.println(schedule);
        return schedule.getId();
    }


    public ScheduleDTO getScheduleById(Long id) {
        return new ScheduleDTO(scheduleRepository.findOne(id));
    }

    public List<ScheduleDTO> getScheduleByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        List<Schedule> schedules = hospital.getSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(new ScheduleDTO(s));
        }
        return scheduleDTOS;
    }

    public List<ScheduleDTO> getScheduleByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Schedule> schedules = doctor.getSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s : schedules){
            scheduleDTOS.add(new ScheduleDTO(s));
        }
        return scheduleDTOS;
    }

    public List<ScheduleDTO> getScheduleByHospitalDoctorId(Long hospitalId, Long doctorId) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        List<Schedule> hospitalSchedules = hospital.getSchedules();

        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Schedule> doctorSchedules = doctor.getSchedules();

        List<ScheduleDTO> hospitalDoctorScheduleDTOS = new ArrayList<>();
        for(Schedule hospitalSchedule : hospitalSchedules){
            for(Schedule doctorSchedule : doctorSchedules)
            {
                if(hospitalSchedule.equals(doctorSchedule)){
                    hospitalDoctorScheduleDTOS.add(new ScheduleDTO(doctorSchedule));
                }
            }
        }
        return hospitalDoctorScheduleDTOS;
    }


    public List<Schedule> getScheduleOfHospitalDoctorId(Long hospitalId, Long doctorId) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        List<Schedule> hospitalSchedules = hospital.getSchedules();

        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Schedule> doctorSchedules = doctor.getSchedules();

        List<Schedule> hospitalDoctorSchedule = new ArrayList<>();
        for(Schedule hospitalSchedule : hospitalSchedules){
            for(Schedule doctorSchedule : doctorSchedules)
            {
                if(hospitalSchedule == doctorSchedule){
                    hospitalDoctorSchedule.add(doctorSchedule);
                }
            }
        }
        return hospitalDoctorSchedule;
    }




    public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {

        Schedule schedule = scheduleRepository.findOne(scheduleDTO.getId());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());

        Days days = dayService.getDayObject(scheduleDTO.getDays().getDay());
        schedule.setDays(days);

        scheduleRepository.save(schedule);

        return new ScheduleDTO(schedule);
    }


    public ScheduleDTO convertIntoTime(ScheduleStringDTO scheduleStringDTO) throws NumberFormatException{
        //scheduleStringDTO.getEndTime().concat(":00");
        ScheduleDTO scheduleDTO=new ScheduleDTO();

//        try {
//            scheduleDTO = new ScheduleDTO(scheduleStringDTO.getId(), new Time(Long.parseLong(scheduleStringDTO.getStartTime().concat(":00"))),
//                    new Time(Long.parseLong(scheduleStringDTO.getEndTime().concat(":00"))), scheduleStringDTO.getDays());
//        }
//        catch (Exception e){
//
//            System.out.println("***********error**********");
//            e.printStackTrace();
//        }
        try {
            Time startTime;
            Time endTime;
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
            java.util.Date date =(java.util.Date)format.parse(scheduleStringDTO.getStartTime().concat(":00"));
            startTime = new Time(date.getTime());
            System.out.println(startTime);
            java.util.Date date1 =(java.util.Date)format.parse(scheduleStringDTO.getEndTime().concat(":00"));
            endTime = new Time(date1.getTime());
            System.out.println(endTime);
            scheduleDTO = new ScheduleDTO(scheduleStringDTO.getId(),startTime,endTime,scheduleStringDTO.getDays());
            return scheduleDTO;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return scheduleDTO;
    }

    public void deleteSchedule(Long scheduleId, Long hospitalId, Long doctorId) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Doctor doctor = doctorRepository.findOne(doctorId);
        Schedule schedule = scheduleRepository.findOne(scheduleId);

        hospital.getSchedules().remove(schedule);
        hospitalRepository.save(hospital);

        doctor.getSchedules().remove(schedule);
        doctorRepository.save(doctor);

        scheduleRepository.delete(schedule.getId());
    }

    public Map<Long, List<ScheduleDTO>> getHospitals(Long doctorId) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        Status status = statusService.getStatusObject("ACTIVE");
        List<Hospital> hospitals = hospitalDoctorRepository.findAllByDoctor(doctor, status);
        Map<Long, List<ScheduleDTO>> stringListMap = new HashMap<>();

        List<HospitalDTO> hospitalDTOS = new ArrayList<>();
        for(Hospital h : hospitals){
            List<ScheduleDTO> scheduleDTO = getScheduleByHospitalDoctorId(h.getId(),doctorId);
            if(scheduleDTO != null){
                stringListMap.put(h.getId(),scheduleDTO);
            }
        }
        return stringListMap;
    }
}

