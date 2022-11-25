import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  updateProfileForm:FormGroup;
  constructor(private userService:UserService , private router:Router) { 
    this.updateProfileForm = new FormGroup(
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
    )
  }

  updateProfile(){

  }

  ngOnInit(): void {
  }

}
