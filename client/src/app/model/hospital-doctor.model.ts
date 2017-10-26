import {Hospital} from "./hospital.model";
import {Doctor} from "./doctor.model";
import {Status} from "./Status.model";

export class HospitalDoctor{
  id: number;
  hospital: Hospital;
  doctor: Doctor;
  status: Status;

  constructor(id?: number, hospital?: Hospital, doctor?: Doctor, status?: Status){
    this.id = id ? id : null;
    this.hospital = hospital ? hospital : new Hospital();
    this.doctor = doctor ? doctor : new Doctor();
    this.status = status ? status : new Status();
  }
}
