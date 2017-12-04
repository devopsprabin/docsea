package org.itglance.docsea.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

        @JsonIgnore
        private City city;
        private String city_name;

        @JsonIgnore
        private District district;
        private String district_name;

        @JsonIgnore
        private Zone zone;
        private String zone_name;

        @JsonIgnore
        private Country country;
        private String counrty_name;


        public AddressDTO() {
        }

        public AddressDTO(Long id, String streetAddress, City city, District district, Zone zone, Country country) {
            this.id = id;
            this.streetAddress = streetAddress;

            this.city = city;
            this.city_name = city.getName();

            this.district = district;
            this.district_name = district.getName();

            this.zone = zone;
            this.zone_name = zone.getName();

            this.country = country;
            this.counrty_name = country.getName();
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

    public String getCity_name() {
        return city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getZone_name() {
        return zone_name;
    }

    public String getCounrty_name() {
        return counrty_name;
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
