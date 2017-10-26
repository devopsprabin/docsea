/**
 * Created by soni on 5/16/2017.
 */
import {Component, OnInit} from '@angular/core';
import {Doctor} from "../model/doctor.model";
import {DoctorService} from "../services/doctor.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SpecialityService} from "../services/speciality.service";
import {QualificationService} from "../services/qualification.service";

declare var swal:any;

@Component({
  selector: 'add-doctor',
  templateUrl: './update-doctor.component.html',
  styleUrls: ['./update-doctor.component.css']
})
export class UpdateDoctorComponent implements OnInit{
  doctor:Doctor;
  photofile:File=null;
  specialityList:any;
  qualificationList:any;

  constructor(private doctorService:DoctorService, private route:ActivatedRoute, private router: Router,
              private specialityService:SpecialityService, private qualificationService:QualificationService){
    this.doctor=new Doctor();
  }

  ngOnInit(){
    this.route.params.subscribe(params=>{
      this.getDoctorDetails(params['id']);
    });
      this.specialityService.getAllSpeciality().subscribe(
        response=>{
          this.specialityList=response;
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
      this.qualificationService.getAllQualification().subscribe(
        response=>{
          this.qualificationList=response;
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

  onChange(event) {
    let file = event.srcElement.files;
    this.photofile=file[0];
  }

  update(){
    let formdata:FormData = new FormData();
    formdata.append('file',this.photofile);
    formdata.append('doctor',JSON.stringify(this.doctor));
    this.doctorService.updateDoctor(formdata).subscribe(
      result => {
      },
      // error=>{
      //   if (!(error.status === 200)) {
      //     swal(
      //       'Oops...',
      //       error._body,
      //       'error'
      //     )
      //   }
      // }
    );
    this.router.navigate(['/hospital-panel']);
  }

  getDoctorDetails(id:number){
    this.doctorService.findById(id).subscribe(
      response=>{
        this.doctor=response;
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
}
