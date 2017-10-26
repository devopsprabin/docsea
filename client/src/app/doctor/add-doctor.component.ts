/**
 * Created by soni on 5/12/2017.
 */
import {Component, OnInit} from '@angular/core';
import {Doctor} from "../model/doctor.model";
import {DoctorService} from "../services/doctor.service";
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";
import {SpecialityService} from "../services/speciality.service";
import {QualificationService} from "../services/qualification.service";

declare var swal:any;

@Component({
  selector: 'add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css'],
})
export class AddDoctorComponent implements OnInit{
  title = 'Add Doctor';
  doctor:Doctor;
  photofile:File;
  specialityList:any;
  qualificationList:any;


  constructor(private doctorService:DoctorService, private authService: AuthenticationService, private router: Router,
              private specialityService:SpecialityService, private qualificationService:QualificationService){
    this.doctor=new Doctor();
  }

  ngOnInit(){
    this.specialityService.getAllSpeciality().subscribe((response)=>{
      this.specialityList=response;
    });
    this.qualificationService.getAllQualification().subscribe((response)=>{
      this.qualificationList=response;
    });
  }

  onChange(event) {
    let file = event.srcElement.files;
    this.photofile=file[0];
  }

  onSubmit(){
    let formdata:FormData=new FormData();
    formdata.append('file',this.photofile);
    formdata.append('doctor',JSON.stringify(this.doctor));
    this.doctorService.addDoctor(formdata).subscribe(
      response=>{
        // console.log(response);
      }
      // error=> {
      //   console.log(error);
      //     if (error.status !== 200) {
      //       swal(
      //         'Oops...',
      //         error._body,
      //         'error'
      //       )
      //     }
      );
    this.router.navigate(['/hospital-panel']);
  }
}
