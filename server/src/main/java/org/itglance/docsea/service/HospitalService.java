package org.itglance.docsea.service;


import java.util.ArrayList;
import java.util.List;

import org.itglance.docsea.config.CryptoUtil;
import org.itglance.docsea.domain.Address;
import org.itglance.docsea.domain.Contact;
import org.itglance.docsea.domain.District;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.HospitalUser;
import org.itglance.docsea.domain.Status;
import org.itglance.docsea.domain.User;
import org.itglance.docsea.repository.AddressRepository;
import org.itglance.docsea.repository.CityRepository;
import org.itglance.docsea.repository.ContactRepository;
import org.itglance.docsea.repository.CountryRepository;
import org.itglance.docsea.repository.DistrictRepository;
import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.repository.HospitalUserRepository;
import org.itglance.docsea.repository.StatusRepository;
import org.itglance.docsea.repository.UserRepository;
import org.itglance.docsea.repository.ZoneRepository;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.HospitalUserDTO;
import org.itglance.docsea.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanj__000 on 5/10/2017.
 */
@Service
@Transactional
public class HospitalService {

    private final Logger log = LoggerFactory.getLogger(HospitalService.class);

    private final HospitalRepository hospitalRepository;

    private final HospitalUserRepository hospitalUserRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final CityRepository cityRepository;

    private final StatusRepository statusRepository;

    private final ContactRepository contactRepository;

    private final CountryRepository countryRepository;

    private final ZoneRepository zoneRepository;

    private final DistrictRepository districtRepository;




    @Autowired
    private final StatusService statusService;


    public HospitalService(HospitalRepository hospitalRepository,
                           StatusRepository statusRepository, HospitalUserRepository hospitalUserRepository
                            , UserRepository userRepository, AddressRepository addressRepository, CityRepository cityRepository
                            , ContactRepository contactRepository, StatusService statusService,CountryRepository countryRepository
                            ,ZoneRepository zoneRepository,DistrictRepository districtRepository) {
        this.hospitalRepository = hospitalRepository;
        this.statusRepository = statusRepository;
        this.hospitalUserRepository = hospitalUserRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.statusService = statusService;
        this.countryRepository = countryRepository;
        this.zoneRepository = zoneRepository;
        this.districtRepository = districtRepository;
    }

    public void registerHospital(HospitalDTO hospitalDTO, UserDTO userDTO){
        Hospital hospital = new Hospital();
        HospitalUser hospitalUser = new HospitalUser();
        User user = new User();
        Status status = new Status();
        Contact contact = new Contact();

        hospital.setLisenceNo(hospitalDTO.getLisenceNo());
        hospital.setName(hospitalDTO.getName());
        hospital.setRegistrationNo(hospitalDTO.getRegistrationNo());

        contact.setContactNumber1(hospitalDTO.getContact().getContactNumber1());
        contact.setContactNumber2(hospitalDTO.getContact().getContactNumber2());
        contact.setEmailId(hospitalDTO.getContact().getEmailId());
        contact.setFax(hospitalDTO.getContact().getFax());
        contact.setWebsite(hospitalDTO.getContact().getWebsite());
        contactRepository.save(contact);
        hospital.setContact(contact);

        Address address = new Address();
        address.setStreetAddress(hospitalDTO.getAddress().getStreetAddress());
        address.setDistrict(districtRepository.findByName(hospitalDTO.getAddress().getDistrict().getName()));
        address.setZone(zoneRepository.findByName(hospitalDTO.getAddress().getZone().getName()));
        address.setCountry(countryRepository.findByName(hospitalDTO.getAddress().getCountry().getName()));
        District district = districtRepository.findByName(hospitalDTO.getAddress().getDistrict().getName());
        address.setCity(cityRepository.findByDistrictAndCityName(district, hospitalDTO.getAddress().getCity().getName()) );
        addressRepository.save(address);
        hospital.setAddress(address);
        hospitalRepository.save(hospital);

        try {
            user.setPassword(CryptoUtil.encrypt(userDTO.getPassword(), userDTO.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setUsername(userDTO.getUsername());
        user.setUserType(1);

        status = statusService.getStatusObject("INACTIVE");
        user.setStatus(status);

        userRepository.save(user);

        hospitalUser.setHospital(hospital);
        hospitalUser.setUser(user);

        log.info(hospitalDTO.getName()+" has been registered into system");

        hospitalUserRepository.save(hospitalUser);

    }

    public boolean isHospitalExist(HospitalDTO hospitalDTO, UserDTO userDTO){
        HospitalDTO hospitalDTOTemp;
        User userTemp;
        userTemp = userRepository.findByUsername(userDTO.getUsername());
        hospitalDTOTemp = hospitalRepository.findByhospitalNameRegLisence(hospitalDTO.getName(),
                hospitalDTO.getRegistrationNo(),hospitalDTO.getLisenceNo());
        if(hospitalDTOTemp != null || userTemp != null){
            return true;
        }

        return false;
    }
    
    public boolean isHospitalExist(Long id){

        HospitalUser hospitalUser = hospitalUserRepository.findOne(id);
        if(hospitalUser!= null){
            return true;
        }
        return false;
    }

    public List<HospitalUserDTO> getAllHospitalUser() {
        List<HospitalUser> hospitalList = hospitalUserRepository.findAll();
        List<HospitalUserDTO> hospitalUserDTOS = new ArrayList<>();
        for(HospitalUser hu: hospitalList){
            hospitalUserDTOS.add(new HospitalUserDTO(hu));
        }
        return hospitalUserDTOS;
    }

    public HospitalUserDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findOne(id);
        HospitalUser hospitalUser = hospitalUserRepository.findByHospital(hospital);
        return new HospitalUserDTO(hospitalUser);
    }

    public HospitalUserDTO getHospitalByUsername(String username) {
        User user = userRepository.findByUsername(username);
        HospitalUser hospitalUser = hospitalUserRepository.findByUser(user);
        String encryptedPassword = hospitalUser.getUser().getPassword();
        try {
            hospitalUser.getUser().setPassword(CryptoUtil.decrypt(encryptedPassword, hospitalUser.getUser().getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HospitalUserDTO(hospitalUser);
    }

    public void updateHospital(HospitalUserDTO hospitalUserDTO){

        HospitalUser hospitalUser = hospitalUserRepository.findOne(hospitalUserDTO.getId());

        Hospital hospital = hospitalRepository.findOne(hospitalUserDTO.getHospital().getId());
        hospital.setName(hospitalUserDTO.getHospital().getName());
        hospital.setRegistrationNo(hospitalUserDTO.getHospital().getRegistrationNo());
        hospital.setLisenceNo(hospitalUserDTO.getHospital().getLisenceNo());

        Contact contact = contactRepository.findOne(hospitalUserDTO.getHospital().getContact().getId());
        contact.setWebsite(hospitalUserDTO.getHospital().getContact().getWebsite());
        contact.setFax(hospitalUserDTO.getHospital().getContact().getFax());
        contact.setEmailId(hospitalUserDTO.getHospital().getContact().getEmailId());
        contact.setContactNumber1(hospitalUserDTO.getHospital().getContact().getContactNumber1());
        contact.setContactNumber2(hospitalUserDTO.getHospital().getContact().getContactNumber2());
        contactRepository.save(contact);
        hospital.setContact(contact);

        Address address = addressRepository.findOne(hospitalUserDTO.getHospital().getAddress().getId());
        address.setStreetAddress(hospitalUserDTO.getHospital().getAddress().getStreetAddress());
        address.setCountry(countryRepository.findByName(hospitalUserDTO.getHospital().getAddress().getCountry().getName()));
        address.setZone(zoneRepository.findByName(hospitalUserDTO.getHospital().getAddress().getZone().getName()));
        address.setDistrict(districtRepository.findByName(hospitalUserDTO.getHospital().getAddress().getDistrict().getName()));
        District district = districtRepository.findByName(hospitalUserDTO.getHospital().getAddress().getDistrict().getName());
        address.setCity(cityRepository.findByDistrictAndCityName(district, hospitalUserDTO.getHospital().getAddress().getCity().getName()) );
        addressRepository.save(address);
        hospital.setAddress(address);
        hospitalUser.setHospital(hospital);


        User user = userRepository.findOne(hospitalUserDTO.getUser().getId());
        user.setUsername(hospitalUserDTO.getUser().getUsername());
        try {
            user.setPassword(CryptoUtil.encrypt(hospitalUserDTO.getUser().getPassword(),hospitalUser.getUser().getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Status status = statusService.getStatusObject(hospitalUserDTO.getUser().getStatus().getStatus());
        user.setStatus(status);
        userRepository.save(user);
        hospitalUser.setUser(user);

        hospitalUserRepository.save(hospitalUser);

}
    //Username validation
    public boolean validateUsernameForUpdate(User user) {
        List<User> users = new ArrayList<>();
        users = userRepository.findAllByUsername(user.getUsername());
        if(users.size() == 0){
            return true;
        }else if(users.size() == 1){
            if(users.get(0).getId() == user.getId()){
                return true;
            }
        }
        return false;
    }

    //Hospital name validation
    public boolean validateHospitalNameForUpdate(Hospital hospital) {
        List<Hospital> hospitals = new ArrayList<>();
        hospitals = hospitalRepository.findAllByName(hospital.getName());
        if(hospitals.size() == 0){
            return true;
        }else if(hospitals.size() == 1){
            if(hospitals.get(0).getId() == hospital.getId()){
                return true;
            }
        }
        return false;
    }

    // Hospital Lisence no. validation
    public boolean validateLisenceForUpdate(Hospital hospital) {
        List<Hospital> hospitals = new ArrayList<>();
        hospitals = hospitalRepository.findAllByLisenceNo(hospital.getLisenceNo());
        if(hospitals.size() == 0){
            return true;
        }else if(hospitals.size() == 1){
            if(hospitals.get(0).getId() == hospital.getId()){
                return true;
            }
        }
        return false;
    }

    //Hospital registration validation
    public boolean validateRegForUpdate(Hospital hospital) {
        List<Hospital> hospitals = new ArrayList<>();
        hospitals = hospitalRepository.findAllByRegistrationNo(hospital.getRegistrationNo());
        if(hospitals.size() == 0){
            return true;
        }else if(hospitals.size() == 1){
            if(hospitals.get(0).getId() == hospital.getId()){
                return true;
            }
        }
        return false;
    }
}
