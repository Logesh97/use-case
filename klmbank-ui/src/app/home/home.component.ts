import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../service/account.model';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  accountDetails:any;
  loginDetail:any = localStorage.getItem("login-status");
  loginStatus:boolean = this.loginDetail !== null;
  constructor(private router:Router , private userService:UserService) { 
  
  }

  ngOnInit(): void {
    let customerId:Number;
    if(!this.loginStatus){
      this.router.navigate(["login"]);
    }else{
      customerId = JSON.parse(localStorage.getItem("login-status") || "{}")['customerId'];
      this.userService.getAccountDetailsByCustomerId(JSON.parse(localStorage.getItem("login-status") || "{}")['customerId']).subscribe({
        next : (res:any) => {
            console.log(res);
            this.accountDetails = res;
        },
        error : (err:any) => {
          console.log("Error [HomeComponent][onInit] : ",err);
          this.router.navigate(["login"]);
        }
      });
    }
  }


}
