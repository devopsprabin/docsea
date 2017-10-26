import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import {HospitalUser} from "../model/hosptial-user.model";
import {Observable} from "rxjs/Observable";
import {ConfigService} from "./config.service";

@Injectable()
export class HospitalService{
  private headers:Headers;
  private hospitalUrl = 'api/hospital/';
  private toggleurl = 'api/status/toggleHospital/';
  token = localStorage.getItem('currentUser');
  private baseUrl:string;

  constructor(private http: Http,private configService:ConfigService){
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', this.token);
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;

  }

  getHospitalByUsername(username: string){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.hospitalUrl+username,options).map(res => res.json());
  }

  addHospital(hospitalUser: HospitalUser){
    const options = new RequestOptions({headers: this.headers});
    return this.http.post(this.baseUrl+this.hospitalUrl,hospitalUser,options).map(res => res.json());
  }

  updateHospital(hospitalUser: HospitalUser){
    const options = new RequestOptions({headers: this.headers});
    return this.http.put(this.baseUrl+this.hospitalUrl,options).map(res => res.json());
  }

  getAllHospitals(){
    return this.http.get(this.baseUrl+this.hospitalUrl).map(res => res.json());
  }

  toggleHospitalStatus(id):Observable<any>{
    return this.http.put(this.baseUrl+this.toggleurl+id,id).map(res => res.json());

  }

}
