/**
 * Created by soni on 5/30/2017.
 */
import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers} from "@angular/http";
import {ConfigService} from "./config.service";

@Injectable()
export class SpecialityService{
  private headers:Headers;
  private specialityUrl = 'api/speciality';
  token = localStorage.getItem('currentUser');
  private baseUrl:string;


  constructor(private http: Http,private configService:ConfigService){
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', this.token);
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;

  }

  getAllSpeciality(){
    // const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.specialityUrl).map(res=>res.json());
  }
}
