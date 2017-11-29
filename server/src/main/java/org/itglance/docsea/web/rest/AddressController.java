package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.*;
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

    public static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Address>> getAddresses( )
    {
        logger.info("get addresses api called  ");
        List<Address> addressList = addressService.getAllAddresses();
        if(addressList == null){

            logger.error("There is no records in address table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/countries")
    public ResponseEntity<List<Country>> getCountries()
    {
        List<Country> countries = addressService.getAllCountries();

        if(countries == null){
            logger.error("There is no records in country table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/zones")
    public ResponseEntity<List<Zone>> getZones()
    {
        logger.info("GET addresses/zones api called  ");
        List<Zone> zones = addressService.getAllZones();

        if(zones == null){
            logger.error("There is no records in zone table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/districts")
    public ResponseEntity<List<District>> getDistricts()
    {
        logger.info("GET addresses/districts api called  ");
        List<District> districts = addressService.getAllDistricts();

        if(districts == null){
            logger.error("There is no records in district table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/cities")
    public ResponseEntity<List<City>> getCities()
    {
        logger.info("GET addresses/districts api called  ");
        List<City> cities = addressService.getAllCities();

        if(cities == null){
            logger.error("There is no records in city table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/zones/{country}")
    public ResponseEntity<List<Zone>> getZonesFromCountry(@PathVariable("country") String country){
        logger.info("Country details");
        logger.info(country);

        List<Zone> zones = addressService.getZonesFromCountry(country);
        if(zones == null){
            logger.error("There is no records in zone table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/districts/{zone}")
    public ResponseEntity<List<District>> getDistrictsFromZone(@PathVariable("zone") String zone){
        logger.info(zone);

        List<District> districts = addressService.getDistrictFromZone(zone);
        if(districts == null){
            logger.error("There is no records in district table.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/cities/{district}")
        public ResponseEntity<List<City>> getCitiesFromDistrict(@PathVariable("district") String district){
            logger.info(district);

            List<City> cities = addressService.getCitiesFromDistrict(district);
            if(cities == null){
                logger.error("There is no records in city table.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
}
