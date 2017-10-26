import {District} from "./district.model";

export class City{
  id: number;
  name: string;
  district: District;

  constructor(id?: number, name?: string, district?: District){
    this.id = id ? id : null;
    this.name = name ? name : null;
    this.district = district ? district : new District();
  }
}
