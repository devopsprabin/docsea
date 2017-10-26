/**
 * Created by sonika on 6/20/17.
 */
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {ConfigService} from "./config.service";


@Injectable()
export class BloodGroupService{
  private bloodGroupUrl="api/bloodGroup";
  private baseUrl:string;

  constructor(private http:Http,private configService:ConfigService){
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
  }

  getBloodGroup(){
    return this.http.get(this.baseUrl+this.bloodGroupUrl).map(response=>response.json());
  }

}
