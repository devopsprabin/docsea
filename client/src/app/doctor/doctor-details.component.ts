import {Component} from "@angular/core";
import {Doctor} from "../model/doctor.model";
import {ScheduleService} from "../services/schedule.service";
import {ActivatedRoute} from "@angular/router";
import {DoctorService} from "../services/doctor.service";
import {Hospital} from "../model/hospital.model";
import {Schedule} from "../model/schedule.model";

declare var swal:any;

@Component({
  selector: "docsea-doctor-details",
  templateUrl: "./doctor-details.component.html",
  styleUrls: ["./doctor-details.component.css"]
})

export class DoctorDetailsComponent{
  doctor:Doctor;
  scheduleMap:any;
  hospitalList:Array<Hospital>;
  scheduleDoubleList:Array<Array<Schedule>>;

  constructor(private scheduleService:ScheduleService, private route:ActivatedRoute, private doctorService:DoctorService) {
      this.doctor=new Doctor();
      this.hospitalList=new Array<Hospital>();
      this.scheduleDoubleList=new Array<Array<Schedule>>();
  }
  ngOnInit(){
    this.route.params.subscribe(params=>{
      this.getDoctor(params['id']);
      this.getHospitals(params['id']);
      this.displayDoctorDetails(params['id']);
    });
  }

  displayDoctorDetails(id){
    this.scheduleService.getDoctorAllHospitalSchedule(id).subscribe(
      response=>{
        this.scheduleMap=response;
        console.log(this.scheduleMap);
        for(let hospital of this.hospitalList){
          for(let schedule of this.scheduleMap[hospital.id]){
            console.log(schedule);
            console.log("******************");

          }
        }
      },
      error=>{
        if (!(error.status === 200)) {
          swal(
            'Oops...',
            error._body,
            'error'
          )
        }
      }
    );
  }

  getDoctor(id){
    this.doctorService.findById(id).subscribe(
      response=>this.onSuccess(response),
      error=>{
        if (!(error.status === 200)) {
          swal(
            'Oops...',
            error._body,
            'error'
          )
        }
      }
    );
  }

  getHospitals(id){
    this.doctorService.getHospitals(id).subscribe(
      response=>{
        this.hospitalList=response;
        console.log(this.hospitalList);
      },
    error=>{
      if (!(error.status === 200)) {
        swal(
          'Oops...',
          error._body,
          'error'
        )
      }
    }
    );
  }

  onSuccess(response){
    this.doctor=response;
  }
}
