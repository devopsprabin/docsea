import {AfterViewInit, Component, DoCheck, OnInit} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {Router} from "@angular/router";
declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck, AfterViewInit{

  token: string;

  public isLoggedIn: boolean;
  public userType : any;
  public username : string;

  constructor(private authService: AuthenticationService, private router: Router){

  }

  ngDoCheck(){
    if( !localStorage.getItem('currentUser') ){
      this.isLoggedIn = false;
    }else{
      this.isLoggedIn = true;
    }
    this.username = localStorage.getItem('username');
    this.userType = localStorage.getItem('userType');
  }

  ngAfterViewInit(){
    $('#navbar ul li').on('click' , function() {
      $('#navbar ul li.active').removeClass('active');
      $(this).addClass('active');
    });
  }

  logout(){
    this.token = localStorage.getItem('currentUser');
    this.authService.logout(this.token).subscribe( (token) => this.onSuccessLogout(token));
  }

  onSuccessLogout(token){
    localStorage.clear();
    this.router.navigate(['/search-doctor']);
  }
}
