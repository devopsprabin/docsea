/**
 * Created by soni on 5/16/2017.
 */
import {Headers, Http, RequestOptions} from "@angular/http";
import {Injectable} from "@angular/core";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";

@Injectable()
export class DoctorService{
  private headers:Headers;

  private hospitalDoctorUrl='api/hospitalDoctor/';
  private doctorUrl='api/doctors/';
  private doctorSearchUrl='api/doctorSearch/';
  private getHospitalUrl='api/hospitalDoctor/';
  private toggleDoctorstatusUrl = 'api/status/toggleDoctor/';
  token = localStorage.getItem('currentUser');
  private baseUrl:string;


  constructor(private http:Http,private configService:ConfigService){
    this.headers= new Headers();
    this.headers.append('Authorization',this.token);
    this.headers.append('Accept','Application/json');
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
    // this.headers.append('Content-Type','mulipart/form-data');a
  }

  addDoctor(formdata:FormData){
    console.log(this.headers);
    const options = new RequestOptions({headers: this.headers});
    return this.http.post(this.baseUrl+this.doctorUrl,formdata,options).map(res=>res.json());
  }

  getHospitalDoctors(){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.hospitalDoctorUrl,options).map(res => res.json() );
  }

  findById(id:number){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.doctorUrl+id,options).map(res=>res.json());
  }

  updateDoctor(formData:FormData){
    const options = new RequestOptions({headers: this.headers});
    return this.http.put(this.baseUrl+this.doctorUrl,formData,options).map(res=>res.json());
  }

  search(term: string): Observable<any>{
    // console.log(term);
    return this.http
      .get(this.baseUrl+this.doctorSearchUrl+'quickSearch/'+term)
      .map(response => response.json());
  }

  searchDoctor(searchTerm: string){
    return this.http.get(this.baseUrl+this.doctorSearchUrl + searchTerm).map(response => response.json());
  }

  deleteDoctor(id : any){
    return this.http.delete(this.baseUrl+this.doctorUrl, id).map(res=>res.json());
  }

  getHospitals(id){
    return this.http.get(this.baseUrl+this.getHospitalUrl+id).map(res=>res.json());
  }

  getDoctorStatus(id: any){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.hospitalDoctorUrl+'/status/'+id,options).map(res => res.json() );
  }

  getHospitalDoctorsListWithStatus(){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.hospitalDoctorUrl+'doctorListWithStatus',options).map(res => res.json() );
  }
  toggledoctorStatus(id):Observable<any>{
    const options = new RequestOptions({headers: this.headers});
    return this.http.put(this.baseUrl+this.toggleDoctorstatusUrl+id,id,options).map(res => res.json());
  }
}
