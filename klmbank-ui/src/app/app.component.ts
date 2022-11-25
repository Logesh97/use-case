import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers : []
})
export class AppComponent {
  title = 'klmbank-ui';
  // loginStatus:boolean;
  // constructor(private router:Router){
  //       this.loginStatus = localStorage.getItem("login-status") !== null
  //       // this.authorMode = this.loginStatus == null
  //       console.log("Login status : ",this.loginStatus);
  // }

  // logIn(){
  //   this.router.navigate(["login"]);
  // }
  // signUp(){
  //   this.router.navigate(["signup"]);
  // }
}
