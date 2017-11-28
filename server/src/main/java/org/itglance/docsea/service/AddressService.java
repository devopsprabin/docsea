package org.itglance.docsea.service;

import java.util.ArrayList;
import java.util.List;

import org.itglance.docsea.domain.Address;
import org.itglance.docsea.domain.City;
import org.itglance.docsea.domain.Country;
import org.itglance.docsea.domain.District;
import org.itglance.docsea.domain.Zone;
import org.itglance.docsea.repository.AddressRepository;
import org.itglance.docsea.repository.CityRepository;
import org.itglance.docsea.repository.CountryRepository;
import org.itglance.docsea.repository.DistrictRepository;
import org.itglance.docsea.repository.ZoneRepository;
import org.itglance.docsea.service.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mahesh on 5/8/2017.
 */

@Service
@Transactional
public class AddressService {
    private final Logger log = LoggerFactory.getLogger(HospitalService.class);
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final ZoneRepository zoneRepository;
    private final CountryRepository countryRepository;
    private final AddressRepository addressRepository;

    public AddressService(CityRepository cityRepository, DistrictRepository districtRepository, ZoneRepository zoneRepository
                        , CountryRepository countryRepository, AddressRepository addressRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.zoneRepository = zoneRepository;
        this.countryRepository = countryRepository;
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses(){

//        List<Address> addressLists =
          return   addressRepository.findAll();
//        List<AddressDTO> addressDTOS = new ArrayList<>();
//        for(Address a: addressLists){
//            addressDTOS.add(new AddressDTO(a));
//        }
//        return addressDTOS;
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();

    }

    public List<Zone> getAllZones(){
        return zoneRepository.findAll();

    }

    public List<District> getAllDistricts(){

        return districtRepository.findAll();

    }

    public List<City> getAllCities(){

        return cityRepository.findAll();


    }

    public List<Zone> getZonesFromCountry(String country){
        Country countryObj = countryRepository.findByName(country);
        return  zoneRepository.findAllByCountry(countryObj);

    }

    public List<District> getDistrictFromZone(String zone){
        Zone zoneObj = zoneRepository.findByName(zone);
        return districtRepository.findAllByZone(zoneObj);

    }

    public List<City> getCitiesFromDistrict(String district){
        District districtObj = districtRepository.findByName(district);
        return cityRepository.findAllByDistrict(districtObj);

    }
}
