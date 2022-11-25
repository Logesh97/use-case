import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllTransactionComponent } from './all-transaction/all-transaction.component';
import { ApplyLoanComponent } from './apply-loan/apply-loan.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';

const routes: Routes = [
  {path:'login' , component : LoginComponent},
  {path:'signup' , component : SignupComponent},
  {path: 'home' , component : HomeComponent},
  {path: 'update-profile' , component : UpdateProfileComponent},
  {path: 'all-transaction' , component : AllTransactionComponent},
  {path: 'apply-loan' , component : ApplyLoanComponent},
  {path : '' , redirectTo : 'home' , pathMatch:'full'} ,
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
