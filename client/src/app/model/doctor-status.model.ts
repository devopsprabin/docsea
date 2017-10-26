import {Status} from "./Status.model";
import {Doctor} from "./doctor.model";

export class DoctorStatus {
  public doctor: Doctor;
  public status: Status;


  constructor(doctor?: Doctor, status?: Status) {
    this.doctor = doctor ? doctor : new Doctor();
    this.status = status ? status : new Status();
  }
}
