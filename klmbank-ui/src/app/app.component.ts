import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers : []
})
export class AppComponent {
  title = 'klmbank-ui';
  constructor(private router:Router){

  }

  logIn(){
    this.router.navigate(["login"]);
  }
  signUp(){
    this.router.navigate(["signup"]);
  }
}
