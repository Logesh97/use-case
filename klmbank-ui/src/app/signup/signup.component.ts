import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  loginStatus:boolean = false;
  signUpForm:FormGroup;
  constructor(private userService:UserService , private router:Router) { 
    this.signUpForm = new FormGroup(
      {
        name : new FormControl("",Validators.required),
        email : new FormControl("",[Validators.required , Validators.email]),
        username : new FormControl("",Validators.required),
        password : new FormControl("",[Validators.required , Validators.maxLength(8)]),
        address: new FormControl("",Validators.required),
        accountType:new FormControl("savings",Validators.required),
        panNumber:new FormControl("",Validators.required),
        branchName:new FormControl("CHENNAI",[Validators.required , Validators.minLength(5)]),
        contactNumber:new FormControl("",Validators.required),
        dateOfBirth:new FormControl(formatDate(Date.now() , 'yyyy-MM-dd','en') , [Validators.required]),
      }
    )
  }

  sigup(){
    console.log("sigup form value");
    console.log(this.signUpForm.value);
    this.userService.signup(this.signUpForm.value).subscribe({
      next : (res:any) => {
          console.log(res);
          this.router.navigate(["login"]);
      },
      error : (err:any) => {
        console.log("Error [SignUpComponent][signUp] : ",err);
      }
    })
  }

  ngOnInit(): void {
  }

}
