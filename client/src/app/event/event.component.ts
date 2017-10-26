import {Component, OnInit} from "@angular/core";
import {Events} from "../model/event.model";
import {EventService} from "../services/event.service";
import {BloodService} from "../services/blood.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";
/**
 * Created by sonika on 6/18/17.
 */
@Component({
  selector: "docsea-event",
  templateUrl: "./event.component.html",
  styleUrls: ["./event.component.css"]
})
export class EventComponent implements OnInit{
  event:Events;
  eventList:any;
  bloodPostList:any;

  constructor(private eventService:EventService, private bloodService:BloodService, private router:Router,
              private authService: AuthenticationService){
    this.event=new Events();
  }

  ngOnInit(){
    this.eventService.getEvents().subscribe((response)=>{
      this.eventList=response;
      console.log(this.eventList);
    });
    this.bloodService.getBloodPost().subscribe((response)=>{
      this.bloodPostList=response;
    });
  }

}
