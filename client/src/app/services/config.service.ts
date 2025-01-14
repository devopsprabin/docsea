import {Injectable} from "@angular/core";
import {Configuration} from "../config/config";
import {environment} from "../../environments/environment";
import {Http} from "@angular/http";

@Injectable()

export class ConfigService {
   private config: Configuration;
   constructor(private http:Http) {}

  load(url:string) {
    return new Promise((resolve) => {
      this.http.get(url).map(res => res.json())
        .subscribe(config => {
          this.config = config;
          resolve();
        });
    });
  }
  getConfiguration():Configuration {

    return this.config;
  }
}

export function ConfigLoader(configService: ConfigService){
	return () => configService.load(environment.configFile);
}


