package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Address;
import org.itglance.docsea.domain.City;
import org.itglance.docsea.domain.District;
import org.itglance.docsea.domain.Zone;
import org.itglance.docsea.domain.Country;

/**
 * Created by Mahesh on 5/8/2017.
 */
public class AddressDTO {
    private Long id;
    private String streetAddress;
    private City city;
    private District district;
    private Zone zone;
    private Country country;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String streetAddress, City city, District district, Zone zone, Country country) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.district = district;
        this.zone = zone;
        this.country = country;
    }

    public AddressDTO(Address address) {
        this(address.getId(),address.getStreetAddress(),address.getCity(),address.getDistrict(),address.getZone(),address.getCountry());
    }

    public Long getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public City getCity() {
        return city;
    }

    public District getDistrict() {
        return district;
    }

    public Zone getZone() {
        return zone;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", city=" + city +
                ", district=" + district +
                ", zone=" + zone +
                ", country=" + country +
                '}';
    }
}
