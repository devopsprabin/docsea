export class User{
  id: number;
  username: string;
  password: string;
  status: string;

  constructor(id?: number, username?: string, password?: string, status?: string){
    this.id = id ? id : null;
    this.username = username ? username : null;
    this.password = password ? password : null;
    this.status = status ? status : null;
  }
}
