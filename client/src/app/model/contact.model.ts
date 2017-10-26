/**
 * Created by soni on 5/14/2017.
 */

export class Contact{
  public id?:number;
  public contactNumber1?: string;
  public contactNumber2?: string;
  public emailId?: string;
  public website?: string;
  public fax?: string;

  constructor(id?:number,contactNumber1?: string,contactNumber2?: string,emailId?: string,website?: string,fax?: string){
    this.id=id?id:null;
    this.contactNumber1 = contactNumber1 ? contactNumber1 : null;
    this.contactNumber2 = contactNumber2 ? contactNumber2 : null;
    this.emailId = emailId ? emailId : null;
    this.website = website ? website : null;
    this.fax = fax ? fax : null;
  }
}
