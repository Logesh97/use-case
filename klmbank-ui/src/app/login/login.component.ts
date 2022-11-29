import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import jwt_decode from 'jwt-decode';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup = new FormGroup({
    username : new FormControl("",Validators.required),
    password : new FormControl("",[Validators.required , Validators.maxLength(8)])
  });
  loginStatus :boolean = false;
  constructor(private userService:UserService , private router : Router) { 
    
  }

  login(){
    console.log(this.loginForm.value);
    let decodeValue ;
      this.userService.login(this.loginForm.value).subscribe({
        next : (res:any) => {
            console.log(res);
            decodeValue = jwt_decode(res['token']);
            console.log("Decoded token : ",decodeValue);
            localStorage.setItem("token" , res['token']);
            localStorage.setItem("login-status" , JSON.stringify(decodeValue));
            // this.header.setAuthorMode(false);
            this.loginStatus = true;
            if(JSON.parse(localStorage.getItem("login-status") || "")['sub']){
              this.router.navigate(["home"]);
            }
        },
        error : (err:any) => {
          console.log("Error [LogInComponent][login] : ",err);
          alert("Username/Password wrong!");
        }
      })
  }

  ngOnInit(): void {
  }

}
