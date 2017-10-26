
import {Contact} from "./contact.model";
import {Address} from "./address.model";
import {Schedule} from "./schedule.model";

export class Hospital{
  id?: number;
  name?: string;
  contact?: Contact;
  address?: Address;
  lisenceNo?: string;
  registrationNo?: string;
  schedules?:Array<Schedule>;

  constructor(id?: number, name?: string, contact?: Contact, address?: Address, lisenceNo?: string, registrationNo?: string, schedules?:Array<Schedule>){
    this.id = id ? id : null;
    this.name = name ? name : null;
    this.contact = contact ? contact : new Contact();
    this.address = address ? address : new Address();
    this.lisenceNo = lisenceNo ? lisenceNo : null;
    this.registrationNo = registrationNo ? registrationNo : null;
    this.schedules=schedules?schedules:new Array<Schedule>();
  }
}
