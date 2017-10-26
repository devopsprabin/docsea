/**
 * Created by sonika on 6/17/17.
 */
import {BloodGroup} from "./blood-group.model";

export class Blood{
  public id?:number;
  public post?:string;
  public bloodGroup?:BloodGroup;
  public deadline?:Date;
  public postDate?:Date;
  public contact?:string;
  public location?:string;

  constructor(id?: number, post?: string, bloodGroup?: BloodGroup,
              deadline?: Date, postDate?: Date, contact?: string, location?:string) {
    this.id = id? id : null;
    this.post = post?post:null;
    this.bloodGroup = bloodGroup?bloodGroup:new BloodGroup();
    this.deadline = deadline?deadline:null;
    this.postDate = postDate?postDate:null;
    this.contact = contact?contact:null;
    this.location=location?location:null;
  }
}
