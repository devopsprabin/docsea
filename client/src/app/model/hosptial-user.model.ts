import {Hospital} from "./hospital.model";
import {User} from "./user.model";

export class HospitalUser{
  id: number;
  hospital: Hospital;
  user: User;

  constructor(id?: number, hospital?: Hospital, user?: User){
    this.id = id ? id : null;
    this.hospital = hospital ? hospital : new Hospital();
    this.user = user ? user : new User();
  }
}
