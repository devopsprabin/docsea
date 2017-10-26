/**
 * Created by soni on 5/18/2017.
 */
import {Component, OnInit} from '@angular/core';
import {Doctor} from "../model/doctor.model";
import {DoctorService} from "../services/doctor.service";
import {ActivatedRoute} from "@angular/router";
import {ScheduleService} from "../services/schedule.service";
import {Days} from "../model/days.model";
import {forEach} from "@angular/router/src/utils/collection";
import {Schedule} from "../model/schedule.model";
import {isNumber} from "util";
declare var $ : any;
declare var swal:any;

@Component({
  selector: 'doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styleUrls: ['./doctor-profile.component.css']
})
export class DoctorProfileComponent implements OnInit{
  doctorList:any;
  doctorScheduleList:any;
  doctor:Doctor;
  days:any;
  schedule:any;
  schedulestat:any;
  tick: boolean = true;
  hide:boolean=true;
  count:number;

  constructor(private doctorService:DoctorService, private route:ActivatedRoute, private scheduleService:ScheduleService){
    this.doctor=new Doctor();
    this.days=['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    this.schedule=[];
  }

  ngOnInit(){
    this.route.params.subscribe(params=>{
      this.getDoctorById(params['id']);
      this.getDoctorSchedule(params['id']);
    })
  }

  getDoctorById(id) {
    this.doctorService.findById(id).subscribe(
      response => {
        this.doctor = response;
      },
      error=>{
        if (!(error.status === 200)) {
          swal(
            'Oops...',
            error._body,
            'error'
          )
        }
      });
  }

  getDoctorSchedule(id){
    this.scheduleService.getHospitalDoctorSchedule(id).subscribe(
      response => {
        this.schedule=Array<Schedule>();
        this.schedulestat=[];
        this.count=0;
        console.log(response);
        for (let day of this.days) {
          if (response.length !== 0) {
            for (let object of response) {
              if (object.days.day == day) {
                this.schedule[this.count] = object;
                this.schedulestat[day] = true;
              }
              else {
                if (!this.schedulestat[day]) {
                  this.schedule[this.count] = new Schedule(null, null, null, new Days(this.count + 1, this.days[this.count]));
                }
              }
            }
            this.count = this.count + 1;
          }
          else {
            this.schedule[this.count] = new Schedule(null, null, null, new Days(this.count + 1, this.days[this.count]));
            this.count=this.count+1;
          }
        }
      this.doctorScheduleList=(this.schedule);
    },
      error=>{
        if (!(error.status === 200)) {
          swal(
            'Oops...',
            error._body,
            'error'
          )
        }
      });
  }

  onEdit(){
    this.tick= !this.tick;
    this.hide=!this.hide;
  }

  onSave(){
    this.tick=!this.tick;
    this.hide=!this.hide;
    this.route.params.subscribe(params=>{
      this.saveSchedule(params['id']);
    })
  }

  saveSchedule(id){
    for(let schedule of this.doctorScheduleList) {
      if(schedule.startTime!==null||schedule.endTime!==null) {
        console.log(schedule);
        this.scheduleService.addSchedule(schedule, id).subscribe(
          response => {},
          error=>{
            if (!(error.status === 200)) {
              swal(
                'Oops...',
                'The Schedule is overlapped',
                error._body,
                'error'
              )
            }
          }
        );
      }
    }
  }
}
