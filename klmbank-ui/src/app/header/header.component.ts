import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() loginStatus:boolean;
  constructor(private router:Router){
        this.loginStatus = localStorage.getItem("login-status") !== null;
        // this.authorMode = this.loginStatus == null
        console.log("Login status : ",this.loginStatus);
  }

  ngOnInit(): void {
    
  }

  logIn(){
    this.router.navigate(["login"]);
  }
  signUp(){
    this.router.navigate(["signup"]);
  }
  signout(){
    localStorage.removeItem("login-status");
    localStorage.removeItem("token");
    this.loginStatus = false;
    this.router.navigate(["home"]);
    // window.location.reload();
    // this.authorMode = false;
}

}
