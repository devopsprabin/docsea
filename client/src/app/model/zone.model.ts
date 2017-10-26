import {Country} from "./country.model";

export class Zone{
  id: number;
  name: string;
  country: Country

  constructor(id?: number, name?: string, country?: Country){
    this.id = id ? id : null;
    this.name = name ? name : null;
    this.country = country ? country : new Country();
  }
}
