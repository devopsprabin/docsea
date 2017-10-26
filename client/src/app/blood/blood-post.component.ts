/**
 * Created by sonika on 6/17/17.
 */
import {Component, OnInit} from "@angular/core";
import {Blood} from "../model/blood.model";
import {BloodService} from "../services/blood.service";
import {Router} from "@angular/router";
import {EventService} from "../services/event.service";
import {BloodGroupService} from "../services/blood-group.service";
declare var $: any;

@Component({
  selector: "docsea-blood-post",
  templateUrl: "./blood-post.component.html",
  styleUrls: ["./blood-post.component.css"]
})
export class BloodPostComponent implements OnInit{
  blood:Blood;
  bloodPostList:Blood[]=[];
  eventList:Event[]=[];
  bloodGroupList:any;

  constructor(private bloodService:BloodService, private eventService:EventService,
              private router:Router, private bloodGroupService:BloodGroupService){
    this.blood=new Blood();
  }

  ngOnInit(){
    this.loadData();
  }

  onSubmit(){
    console.log(this.blood);
    this.bloodService.addBloodPost(this.blood).subscribe((response)=>{
      console.log(response);
      this.onSuccess(response);
    }, (err)=>{console.log(err)});
  }

  onSuccess(res){
    $('#myModal').modal('hide');
    this.loadData();
    this.router.navigate(['/blood-post']);
  }

  loadData(){
    this.bloodGroupService.getBloodGroup().subscribe((response)=>{
      this.bloodGroupList=response;
    });
    this.bloodService.getBloodPost().subscribe((response)=>{
      this.bloodPostList=response;
    });
    this.eventService.getEvents().subscribe((response)=>{
      this.eventList=response;
      console.log("event response",this.eventList);
    });
  }
}
