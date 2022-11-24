import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;
  constructor(private userService:UserService , private router : Router) { 
    this.loginForm = new FormGroup({
      username : new FormControl("",Validators.required),
      password : new FormControl("",[Validators.required , Validators.minLength(8)])
    })
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
            if(JSON.parse(localStorage.getItem("login-status") || "")['sub']){
              this.router.navigate(["home",JSON.parse(localStorage.getItem("login-status") || "")['sub']]);
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
