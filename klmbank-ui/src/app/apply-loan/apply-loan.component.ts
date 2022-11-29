import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-apply-loan',
  templateUrl: './apply-loan.component.html',
  styleUrls: ['./apply-loan.component.css']
})
export class ApplyLoanComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  loanApplication : FormGroup = this.initForm();
  constructor(private userService : UserService , private router: Router) { }

  initForm(){
    return new FormGroup(
      {
        customerId : new FormControl(JSON.parse(localStorage.getItem("login-status") || "{}")['customerId'] , Validators.required),
        requestAmount : new FormControl( "", [Validators.required , Validators.min(100) , Validators.max(1000000)])
      }
    )
  }
  ngOnInit(): void {
  }

  applyLoan(){
    console.log("apply loan form value");
    console.log(this.loanApplication.value);
    this.userService.applyLoan(this.loanApplication.value).subscribe({
      next : (res:any) => {
          console.log(res);
          this.router.navigate(["all-transaction"]);
      },
      error : (err:any) => {
        console.log("Error [ApplyLoan][login] : ",err);
      }
    })
  }

}
