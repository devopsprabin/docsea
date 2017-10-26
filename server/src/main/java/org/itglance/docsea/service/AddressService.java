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

    public List<AddressDTO> getAllAddresses(){

        List<Address> addressLists = addressRepository.findAll();
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for(Address a: addressLists){
            addressDTOS.add(new AddressDTO(a));
        }
        return addressDTOS;
    }

    public List<String> getAllCountries(){

        List<String> countryList = new ArrayList<>();

        List<Country> countryListObj = countryRepository.findAll();
        for(Country c : countryListObj){
            countryList.add(c.getName());
        }
        System.out.println(countryList);
        return countryList;
    }

    public List<String> getAllZones(){
        List<Zone> zoneListObj;
        List<String> zoneList = new ArrayList<>();

        zoneListObj = zoneRepository.findAll();
        for(Zone zone: zoneListObj){
            zoneList.add(zone.getName());
        }
        return zoneList;
    }

    public List<String> getAllDistricts(){
        List<District> districtListObj;
        List<String> districtList = new ArrayList<>();

        districtListObj = districtRepository.findAll();
        for(District district: districtListObj){
            districtList.add(district.getName());
        }
        return districtList;
    }

    public List<String> getAllCities(){
        List<City> cityListObj;
        List<String> cityList = new ArrayList<>();

        cityListObj = cityRepository.findAll();
        for(City city: cityListObj){
            cityList.add(city.getName());
        }
        return cityList;
    }

    public List<String> getZonesFromCountry(String country){
        Country countryObj = countryRepository.findByName(country);
        List<Zone> zonesObjlist = zoneRepository.findAllByCountry(countryObj);
        List<String> zonesList = new ArrayList<>();
        for(Zone zone: zonesObjlist){
            zonesList.add(zone.getName());
        }
        return zonesList;
    }

    public List<String> getDistrictFromZone(String zone){
        Zone zoneObj = zoneRepository.findByName(zone);
        List<District> districtObjlist = districtRepository.findAllByZone(zoneObj);
        List<String> districtsList = new ArrayList<>();
        for(District district: districtObjlist){
            districtsList.add(district.getName());
        }
        return districtsList;
    }

    public List<String> getCitiesFromDistrict(String district){
        District districtObj = districtRepository.findByName(district);
        List<City> cityObjList = cityRepository.findAllByDistrict(districtObj);
        List<String> citiesList = new ArrayList<>();
        for(City city: cityObjList){
            citiesList.add(city.getName());
        }
        return citiesList;
    }
}
