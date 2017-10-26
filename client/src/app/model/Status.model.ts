export class Status{
  id: number;
  status: string;

  constructor(id?: number, status?: string){
    this.id = id ? id : null;
    this.status = status ? status : null;
  }
}
