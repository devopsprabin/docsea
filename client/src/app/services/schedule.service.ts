import {Injectable} from "@angular/core";
import {Http, RequestOptions,Headers} from "@angular/http";
import {Schedule} from "../model/schedule.model";
import {ConfigService} from "./config.service";
/**
 * Created by soni on 5/28/2017.
 */
@Injectable()
export class ScheduleService{

  private headers:Headers;
  private scheduleUrl='api/schedules/';
    private doctorHospitalUrl='api/schedules/hospitalSchedule/';
  token = localStorage.getItem('currentUser');
  private baseUrl:string;


  constructor(private http:Http,private configService:ConfigService){
    this.headers=new Headers;
    this.headers.append('Authorization',this.token);
    this.headers.append('Accept','Application/json');
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
  }

  addSchedule(schedule:Schedule,id:number){
    const options = new RequestOptions({headers: this.headers});
    return this.http.put(this.baseUrl+this.scheduleUrl+id,schedule,options).map(res=>res.json());
  }

  getDoctorAllHospitalSchedule(id){
    const options = new RequestOptions({headers: this.headers});
    console.log(id);
    return this.http.get(this.baseUrl+this.doctorHospitalUrl+id,options).map(res=>res.json());
  }

  getHospitalDoctorSchedule(id:number){
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.scheduleUrl+'hospitalDoctor/'+id,options).map(res=>res.json());
  }

}
