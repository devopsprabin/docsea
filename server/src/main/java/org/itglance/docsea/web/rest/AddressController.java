package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.Address;
import org.itglance.docsea.service.AddressService;
import org.itglance.docsea.service.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mahesh on 5/8/2017.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    public static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @GetMapping(value = "/addresses")
    public ResponseEntity<?> getAddresses( )
    {
        logger.info("get addresses api called  ");
        List<AddressDTO> addressDto = addressService.getAllAddresses();
        if(addressDto == null){
            logger.error("There is no records in address table.");
            return new ResponseEntity(("There is no records in address table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity< List<AddressDTO>> (addressDto, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/countries")
    public ResponseEntity<?> getCountries()
    {
        List<String> countries = addressService.getAllCountries();
        if(countries == null){
            logger.error("There is no records in country table.");
            return new ResponseEntity(("There is no records in country table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<String>> (countries, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/zones")
    public ResponseEntity<?> getZones()
    {
        List<String> zones = addressService.getAllZones();
        if(zones == null){
            logger.error("There is no records in zone table.");
            return new ResponseEntity(("There is no records in zone table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<String>> (zones, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/districts")
    public ResponseEntity<?> getDistricts()
    {
        List<String> districts = addressService.getAllDistricts();
        if(districts == null){
            logger.error("There is no records in district table.");
            return new ResponseEntity(("There is no records in district table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<String>> (districts, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/cities")
    public ResponseEntity<?> getCities()
    {
        List<String> cities = addressService.getAllCities();
        if(cities == null){
            logger.error("There is no records in city table.");
            return new ResponseEntity(("There is no records in city table."), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<String>> (cities, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/zones/{country}")
    public ResponseEntity<?> getZonesFromCountry(@PathVariable("country") String country){
        logger.info(country);

        System.out.println(country);
        List<String> zones = addressService.getZonesFromCountry(country);
        return new ResponseEntity<> (zones, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/districts/{zone}")
    public ResponseEntity<?> getDistrictsFromZone(@PathVariable("zone") String zone){
        logger.info(zone);

        List<String> districts = addressService.getDistrictFromZone(zone);
        return new ResponseEntity<List<String>>(districts, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/cities/{district}")
        public ResponseEntity<?> getCitiesFromDistrict(@PathVariable("district") String district){
            logger.info(district);

            List<String> cities = addressService.getCitiesFromDistrict(district);
            return new ResponseEntity<List<String>>(cities, HttpStatus.OK);
        }
}
