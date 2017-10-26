import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Events} from "../model/event.model";
import {ConfigService} from "./config.service";
/**
 * Created by sonika on 6/19/17.
 */
@Injectable()
export class EventService{
  public eventUrl='api/events';
  private headers:Headers;
  private baseUrl:string;

  token = localStorage.getItem('currentUser');

  constructor(private http:Http,private configService:ConfigService){
    this.headers= new Headers();
    this.headers.append('Authorization',this.token);
    this.headers.append('Accept','Application/json');
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;

  }

  addEvent(event:Events){
    const options = new RequestOptions({headers:this.headers});
    return this.http.post(this.baseUrl+this.eventUrl,event,options).map(res=>res.toString());
  }

  getEvents(){
    return this.http.get(this.baseUrl+this.eventUrl).map(res=>res.json());
  }
}
