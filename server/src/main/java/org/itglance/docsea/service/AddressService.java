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
import org.itglance.docsea.service.dto.*;
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

//    private AddressDTO addressDTO;

    public AddressService(CityRepository cityRepository, DistrictRepository districtRepository, ZoneRepository zoneRepository
                        , CountryRepository countryRepository, AddressRepository addressRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.zoneRepository = zoneRepository;
        this.countryRepository = countryRepository;
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> getAllAddresses(){

        List<Address> addressList = addressRepository.findAll();
        List<AddressDTO> addressDTOList = new ArrayList<>();
        for(Address address: addressList){
            addressDTOList.add(new AddressDTO(address));
        }
        return addressDTOList;
    }

    public List<CountryDTO> getAllCountries(){
        List<Country> countryList = countryRepository.findAll();
        List<CountryDTO> countryDTOList = new ArrayList<>();
        for(Country country: countryList){
            countryDTOList.add(new CountryDTO(country));
        }
        return countryDTOList;
//        return countryRepository.findAll();

    }

    public List<ZoneDTO> getAllZones(){
//        return zoneRepository.findAll();
        List<Zone> zoneList = zoneRepository.findAll();
        List<ZoneDTO> zoneDTOList = new ArrayList<>();
        for(Zone zone: zoneList){
            zoneDTOList.add(new ZoneDTO(zone));
        }
        return zoneDTOList;


    }

    public List<DistrictDTO> getAllDistricts(){


//        return districtRepository.findAll();
        List<District> districtList= districtRepository.findAll();
        List<DistrictDTO> districtDTOList= new ArrayList<>();
        for(District d: districtList){
            districtDTOList.add(new DistrictDTO(d));
        }
        return districtDTOList;

    }

    public List<CityDTO> getAllCities(){

//        return cityRepository.findAll();
        List<City> cityList= cityRepository.findAll();
        List<CityDTO> cityDTOList= new ArrayList<>();
        for(City city: cityList){
            cityDTOList.add(new CityDTO(city));
        }
        return cityDTOList;

    }

    public List<ZoneDTO> getZonesFromCountry(String country){
        Country countryObj = countryRepository.findByName(country);
//        return  zoneRepository.findAllByCountry(countryObj);

        List<Zone> zoneList= zoneRepository.findAllByCountry(countryObj);
        List<ZoneDTO> zoneDTOList = new ArrayList<>();
        for(Zone zone: zoneList){
            zoneDTOList.add(new ZoneDTO(zone));
        }
        return zoneDTOList;

    }

    public List<DistrictDTO> getDistrictFromZone(String zone){
        Zone zoneObj = zoneRepository.findByName(zone);
//        return districtRepository.findAllByZone(zoneObj);

        List<District> districtList= districtRepository.findAllByZone(zoneObj);
        List<DistrictDTO> districtDTOList= new ArrayList<>();
        for(District district: districtList){
            districtDTOList.add(new DistrictDTO(district));
        }
        return districtDTOList;

    }

    public List<CityDTO> getCitiesFromDistrict(String district){
        District districtObj = districtRepository.findByName(district);
//        return cityRepository.findAllByDistrict(districtObj);

        List<City> cityList= cityRepository.findAllByDistrict(districtObj);
        List<CityDTO> cityDTOList= new ArrayList<>();
        for(City city: cityList){
            cityDTOList.add(new CityDTO(city));
        }
        return cityDTOList;

    }
}
