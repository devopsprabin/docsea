export class LoginModel {
  username: string;
  password: string;

  constructor(username?: string, password?: string) {
    this.username = username ? username : null;
    this.password = password ? password : null;
  }
}
