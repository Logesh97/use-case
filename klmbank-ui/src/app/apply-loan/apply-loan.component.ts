import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-apply-loan',
  templateUrl: './apply-loan.component.html',
  styleUrls: ['./apply-loan.component.css']
})
export class ApplyLoanComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  constructor() { }

  ngOnInit(): void {
  }

}
