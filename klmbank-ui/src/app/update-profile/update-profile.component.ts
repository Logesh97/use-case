import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from '../service/account.model';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  updateProfileForm:FormGroup  = this.initForm();
  accountDetails:any;
  constructor(private userService:UserService , private router:Router) { 
  }

   initForm(){
    return new FormGroup(
      {
        name : new FormControl("",Validators.required),
        email : new FormControl("",[Validators.required , Validators.email]),
        username : new FormControl("",Validators.required),
        password : new FormControl("",[Validators.required , Validators.minLength(8)]),
        address: new FormControl("",Validators.required),
        accountType:new FormControl("savings",Validators.required),
        panNumber:new FormControl("",Validators.required),
        branchName:new FormControl("",Validators.required),
        contactNumber:new FormControl("",Validators.required),
        dateOfBirth:new FormControl(formatDate(Date.now() , 'yyyy-MM-dd','en') , Validators.required),
      }
    );
   }

  setFormValue(account: Account){
    this.updateProfileForm.setValue(
      {
        name : account.name,
        email : account.email,
        username : account.username,
        password : account.password,
        address: account.address,
        accountType:account.accountType,
        panNumber:account.panNumber,
        branchName:account.branchName,
        contactNumber:account.contactNumber,
        dateOfBirth:account.dateOfBirth,
      }
    );
  }

  updateProfile(){
    console.log("update profile form value");
    console.log(this.updateProfileForm.value);
    this.userService.signup(this.updateProfileForm.value).subscribe({
      next : (res:any) => {
          console.log(res);
          this.router.navigate(["home"]);
      },
      error : (err:any) => {
        console.log("Error [UpdateProfileComponent][updateProfile] : ",err);
      }
    })
  }

  ngOnInit(): void {
    let customerId:Number;
    if(!this.loginStatus){
      this.router.navigate(["login"]);
    }else{
      customerId = JSON.parse(localStorage.getItem("login-status") || "{}")['customerId'];
      this.userService.getAccountDetailsByCustomerId(customerId).subscribe({
        next : (res:any) => {
            console.log(res);
            this.accountDetails = res;
            this.setFormValue(this.accountDetails);
        },
        error : (err:any) => {
          console.log("Error [UpdateProfileComponent][onInit] : ",err);
          this.router.navigate(["login"]);
        }
      });
    }
  }

}
