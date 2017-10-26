/**
 * Created by soni on 5/30/2017.
 */
import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers} from "@angular/http";
import {ConfigService} from "./config.service";

@Injectable()
export class QualificationService{
  private headers:Headers;
  private qualificationUrl = 'api/qualification';
  private baseUrl:string;

  token = localStorage.getItem('currentUser');

  constructor(private http: Http,private configService:ConfigService){
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', this.token);
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
  }

  getAllQualification(){
    // const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.qualificationUrl).map(res=>res.json());
  }
}
