/**
 * Created by soni on 5/22/2017.
 */
export class Speciality{
  public id?:number;
  public name?:string;

  constructor(id?:number,name?:string){
    this.id=id?id:null;
    this.name=name?name:null;
  }
}
