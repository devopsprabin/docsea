/**
 * Created by soni on 5/30/2017.
 */
export class Qualification{
  public id?:number;
  public name?:string;

  constructor(id?: number, name?: string) {
    this.id = id?id:null;
    this.name = name?name:null;
  }
}
