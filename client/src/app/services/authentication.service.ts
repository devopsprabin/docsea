import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {User} from "../model/user.model";
import {ConfigService} from "./config.service";

@Injectable()
export class AuthenticationService{
  private headers:Headers;

  private loginUrl='api/';
  private baseUrl:string;

  constructor(private http:Http,private configService:ConfigService){
    this.headers= new Headers();
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
    // this.headers.append('Content-Type','mulipart/form-data');
    // this.headers.append('Accept','application/json');
  }

  login(user: User)
  {
    // console.log(user);
    return this.http.post(this.baseUrl+this.loginUrl+"login",user).map(res => res.json());
  }

  logout(token: string){
    // console.log(user);
    return this.http.post(this.baseUrl+this.loginUrl+"logout",token).map(res => res);
  }
}









