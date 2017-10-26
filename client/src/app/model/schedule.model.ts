/**
 * Created by soni on 5/28/2017.
 */
import {Days} from "app/model/days.model";

export class Schedule{
  id?:number;
  startTime?:any;
  endTime?:any;
  days?:Days;


  constructor(id?: number, startTime?: any, endTime?: any, days?: Days) {
    this.id = id?id:null;
    this.startTime = startTime?startTime:null;
    this.endTime = endTime?endTime:null;
    this.days = days?days:new Days();
  }
}
