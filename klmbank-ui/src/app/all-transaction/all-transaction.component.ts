import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-transaction',
  templateUrl: './all-transaction.component.html',
  styleUrls: ['./all-transaction.component.css']
})
export class AllTransactionComponent implements OnInit {

  loginStatus:boolean = localStorage.getItem("login-status") !== null;
  constructor() { }

  ngOnInit(): void {
  }

}
