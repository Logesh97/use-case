import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Account } from "./account.model";
import { User } from "./user.model";

const apiGateway = environment.apiGateway;
@Injectable({
    providedIn:'root'
})
export class UserService{
    
    apiUrl:string = apiGateway+"customer";
    constructor(private http:HttpClient){
    }
    
    
    signup(account: Account) {
        let signUpUrl = this.apiUrl+"/register";
        return this.http.post(signUpUrl , account);
    }
    updateProfile(account: Account) {
        let url = this.apiUrl+"/updateProfile";
        return this.http.put(url,account ,{headers : {
            "Authorization" : "Bearer "+ localStorage.getItem('token')
        }});
    }
    
    login(user:User){
        let loginUrl = this.apiUrl+"/login";
        return this.http.post(loginUrl , user);
    }
    getAccountDetailsByCustomerId(customerId: Number) {
        if(customerId === undefined){
            customerId = JSON.parse(localStorage.getItem("login-status") || "{}")['customerId'];
        }
        let url = this.apiUrl+"/"+customerId;
        // if(customerId !== undefined){
            return this.http.get(url,{headers : {
                "Authorization" : "Bearer "+ localStorage.getItem('token')
            }});

        // }else{
            // return ;
        // }
    }
    getTransactionDetailsByCustomerId(customerId : Number){
        let url = this.apiUrl+"/all-transaction/"+customerId;
        return this.http.get(url,{headers : {
            "Authorization" : "Bearer "+ localStorage.getItem('token')
        }});
    }
    
    applyLoan(value: any) {
        let url = this.apiUrl+"/apply-loan";
        return this.http.post(url,value ,{headers : {
            "Authorization" : "Bearer "+ localStorage.getItem('token')
        }});
    }
}