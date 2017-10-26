import {Zone} from "./zone.model";

export class District{
  id: number;
  name: string;
  zone: Zone;

  constructor(id?: number, name?: string, zone?: Zone){
    this.id = id ? id : null;
    this.name = name ? name : null;
    this.zone = zone ? zone : new Zone();
  }
}
