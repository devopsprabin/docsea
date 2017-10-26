import {City} from "./city.model";
import {District} from "./district.model";
import {Zone} from "./zone.model";
import {Country} from "./country.model";

export class Address{
  id: number;
  streetAddress: String;
  city: City;
  district: District;
  zone: Zone;
  country: Country;

  constructor(id?: number, streetAddress?: string, city?: City, district?: District, zone?: Zone, country?: Country ){
    this.id = id ? id : null;
    this.streetAddress = streetAddress ? streetAddress : null;
    this.city = city ? city : new City();
    this.district = district ? district : new District();
    this.zone = zone ? zone : new Zone();
    this.country = country ? country : new Country();
  }
}
