/**
 * Created by sonika on 6/18/17.
 */
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Blood} from "../model/blood.model";
import {ConfigService} from "./config.service";
@Injectable()
export class BloodService{
  private bloodUrl="api/bloodPost";
  private baseUrl:string;
  constructor(private http:Http,private configService:ConfigService){
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
  }

  addBloodPost(blood){
    return this.http.post(this.baseUrl+this.bloodUrl,blood);
  }

  getBloodPost(){
    return this.http.get(this.baseUrl+this.bloodUrl).map(res=>
      res.json()
      // console.log("********************************************************");
      // console.log(res.);
    );
  }
}
