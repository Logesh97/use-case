import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-all-transaction',
  templateUrl: './all-transaction.component.html',
  styleUrls: ['./all-transaction.component.css']
})
export class AllTransactionComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  transactions:any;
  constructor(private router:Router , private userService: UserService) { }

  ngOnInit(): void {
    let customerId:Number;
    if(!this.loginStatus){
      this.router.navigate(["login"]);
    }else{
      customerId = JSON.parse(localStorage.getItem("login-status") || "{}")['customerId'];
      this.userService.getTransactionDetailsByCustomerId(customerId).subscribe({
        next : (res:any) => {
            console.log(res);
            this.transactions = res;
        },
        error : (err:any) => {
          console.log("Error [AllTransactionComponent][onInit] : ",err);
          this.router.navigate(["home"]);
        }
      });
    }
  }

}
