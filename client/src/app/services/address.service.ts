import {Injectable} from "@angular/core";
import {Http, RequestOptions, Response,Headers} from "@angular/http";

import {Observable} from "rxjs/Observable";
import {ConfigService} from "./config.service";

@Injectable()
export class AddressService{
  private headers: Headers;
  private addressUrl='api/addresses';
  private baseUrl:string;
  token = localStorage.getItem('currentUser');
  constructor(private http:Http,private configService:ConfigService){
    this.headers = new Headers();
    this.headers.append('Accept','application/json');
    this.headers.append('Authorization', this.token);
    this.baseUrl=this.configService.getConfiguration().webApiBaseUrl;
  }

  getCountries(): Observable<any>{
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.addressUrl+"/countries",options)
      .map((res: Response) => res.json());
  }

  getZones(): Observable<any> {
    return this.http.get(this.baseUrl+this.addressUrl+"/zones")
      .map((res: Response) => res.json());
  }

  getZonesOfCountry(country: string): Observable<any> {
    const options = new RequestOptions({headers: this.headers});
    // console.log(country);
    return this.http.get(this.baseUrl+this.addressUrl+"/zones/"+country,options).map((res: Response) => res.json());

  }

  getDistricts(): Observable<any> {
    return this.http.get(this.baseUrl+this.addressUrl+"/districts")
      .map((res: Response) => res.json());
  }

  getDistrictsOfZone(zone: string): Observable<any> {
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.addressUrl+"/districts/"+zone,options).map((res: Response) => res.json());
  }

  getCities(): Observable<any> {
    return this.http.get(this.baseUrl+this.addressUrl+"/cities")
      .map((res: Response) => res.json());
  }

  getCitiesOfDistrict(district: string): Observable<any> {
    const options = new RequestOptions({headers: this.headers});
    return this.http.get(this.baseUrl+this.addressUrl+"/cities/"+district,options).map((res: Response) => res.json());
  }


}
