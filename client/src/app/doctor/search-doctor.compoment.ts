import {Component, OnInit} from "@angular/core";

import { Observable }   from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Http} from "@angular/http";
import {DoctorService} from "../services/doctor.service";
import {HospitalDoctor} from "../model/hospital-doctor.model";
import {Router} from "@angular/router";

declare var swal:any;

@Component({
  selector: 'docsea-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls : ['./search-doctor.component.css']
})

export class SearchDoctorComponent implements OnInit{
  searchDoctor: string = '';
  hospitalDoctorList: any;
  doctorList: HospitalDoctor;

  constructor(private doctorService: DoctorService, private router: Router) {}

  ngOnInit(){
  }

  search(term: string): void {
    if(term.length != 0) {
      this.doctorService.search(term)
        .subscribe(
          result => {
            this.hospitalDoctorList = result;
          },
          error => {
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
  }

  doctorDetails(doctor: string): void{
    // console.log(doctor);
    this.router.navigate(['/doctor-view',doctor]);
  }
  selectString(hospitalDoctor: string){
    this.searchDoctor = hospitalDoctor;
  }

}
