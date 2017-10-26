import {AfterViewInit, Component, OnInit} from "@angular/core";
import {DoctorService} from "../services/doctor.service";
import {Events} from "../model/event.model";
import {EventService} from "../services/event.service";
import {Router} from "@angular/router";
import {BloodService} from "../services/blood.service";
import {DoctorStatus} from "../model/doctor-status.model";

declare var $: any;
declare var swal:any;

@Component({
  selector: 'docsea-hospital-panel',
  templateUrl: './hospital-panel.component.html',
  styleUrls: ['./hospital-panel.component.css']
})

export class HospitalPanelComponent implements OnInit, AfterViewInit{

  doctorList: any;
  event:Events;
  eventList:any;
  bloodPostList:any;
  element:any;

  constructor(private doctorService:DoctorService, private eventService:EventService,
              private router:Router, private bloodService:BloodService){
    this.event=new Events();
  }

  ngOnInit(){
    this.getAllDoctorList();
  }

  getAllDoctorList(){
    this.doctorService.getHospitalDoctorsListWithStatus().subscribe(
      data=>{
        this.doctorList = data;
      },
      response=>{
        if (!(response.status === 200 || response.status === 500)) {
          swal(
            'Oops...',
            response._body,
            'error'
          )
        }
      }
    )}

  ngAfterViewInit() {
    setTimeout(() => $("#hospital-panel-table").dataTable(),1500);
  }

  onSubmit(){
    console.log("inside submit function");
    this.eventService.addEvent(this.event).subscribe(response=>{
       $('#myEventModal').modal('hide');
    },(response)=>{
        console.log(response);
        if(!(response.status == 200)){
        swal(
          'Oops...',
          response._body,
          'error'
        )
        }else{
          swal(
            'Sucessful',
            response._body,
            'success'
          )
        }
    });
    this.eventService.getEvents().subscribe((response)=>{
      this.eventList=response;
    });
    this.bloodService.getBloodPost().subscribe((response)=>{
      this.bloodPostList=response;
    });

    this.router.navigate(['/hospital-panel']);
  }

  toggleStatus(id: number){
    this.doctorService.toggledoctorStatus(id).subscribe(
      result => { this.ontogglesucess()
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

 /* getDoctorStatus(id: number){
    this.
  }*/
  private ontogglesucess() {
    this.getAllDoctorList();
  }
}


