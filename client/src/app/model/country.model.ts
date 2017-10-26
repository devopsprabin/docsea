export class Country{
  id: number;
  name: string;

  constructor(id?: number, name?: string){
    this.id = id ? id : null;
    this.name = name ? name : null;
  }
}
