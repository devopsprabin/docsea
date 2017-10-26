package org.itglance.docsea.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.itglance.docsea.domain.Doctor;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.Session;
import org.itglance.docsea.domain.Status;
import org.itglance.docsea.repository.AddressRepository;
import org.itglance.docsea.repository.CityRepository;
import org.itglance.docsea.repository.CountryRepository;
import org.itglance.docsea.repository.DistrictRepository;
import org.itglance.docsea.repository.DoctorRepository;
import org.itglance.docsea.repository.HospitalDoctorRepository;
import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.repository.SpecialityRepository;
import org.itglance.docsea.repository.ZoneRepository;
import org.itglance.docsea.service.dto.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sriyanka on 5/25/2017.
 */

@Service
@Transactional
public class DoctorSearchService {

    private final HospitalDoctorRepository hospitalDoctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final CountryRepository countryRepository;
    private final ZoneRepository zoneRepository;
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final SpecialityRepository specialityRepository;
    private final AddressRepository addressRepository;

    @Autowired
    SessionService sessionService;
    @Autowired
    StatusService statusService;

    public DoctorSearchService(HospitalDoctorRepository hospitalDoctorRepository, HospitalRepository hospitalRepository
                            , DoctorRepository doctorRepository, CountryRepository countryRepository, ZoneRepository zoneRepository
            , DistrictRepository districtRepository, CityRepository cityRepository, SpecialityRepository specialityRepository
                ,AddressRepository addressRepository) {
        this.hospitalDoctorRepository = hospitalDoctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.countryRepository = countryRepository;
        this.zoneRepository = zoneRepository;
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
        this.specialityRepository = specialityRepository;
        this.addressRepository=addressRepository;
    }

    public List<DoctorDTO> findDoctor(String searchString){
        List<Doctor> doctors=hospitalDoctorRepository.findDoctorByString(searchString);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        int flag = 0;
       for(int i=0; i<doctors.size();i++){
           for(int j=i+1; j < doctors.size(); j++){
               if(doctors.get(i) == doctors.get(j)){
                   flag =1;
               }
           }
           if(flag == 0){
               doctorDTOS.add(new DoctorDTO(doctors.get(i)));
           }
           flag= 0;
       }
        System.out.println(doctorDTOS);
        System.out.println("************************************");
        System.out.println(doctors);


//
        return doctorDTOS;
    }

    public List<DoctorDTO> findAllDoctorsOfHospital(String token) {
        Session session = sessionService.checkSession(token);
        Hospital hospital = hospitalRepository.findOne(session.getHospitalId());
        Status status = statusService.getStatusObject("DEACTIVE");
        List<Doctor> doctors = hospitalDoctorRepository.findAllByHospital(hospital, status);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor d: doctors){
            doctorDTOS.add(new DoctorDTO(d));
        }
        return doctorDTOS;
    }

    // Quick search ..........

    public List<String> getStringListForSearch(String str){
         List<String> combineResult = new ArrayList<>();
        combineResult.clear();

        List<String> doctorResult = doctorRepository .findThisDoctor(str);
        combineResult.addAll(doctorResult);

        List<String> specialityResult = specialityRepository .findThisDoctor(str);
        combineResult.addAll(specialityResult);

        List<String> hospitalResult = hospitalRepository.findThisDoctor(str);
        combineResult.addAll(hospitalResult);

        List<String> addressResult = addressRepository.findThisDoctor(str);
        combineResult.addAll(addressResult);

        System.out.println(combineResult);
        Collections.sort(combineResult);
        return combineResult;
    }
}
