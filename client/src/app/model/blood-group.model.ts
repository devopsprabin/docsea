/**
 * Created by sonika on 6/18/17.
 */
export class BloodGroup{
  id?:number;
  bloodGroup?:string;
  image?:string;


  constructor(id?: number, bloodGroup?: string, image?: string) {
    this.id = id?id:null;
    this.bloodGroup = bloodGroup?bloodGroup:null;
    this.image = image?image:null;
  }
}
