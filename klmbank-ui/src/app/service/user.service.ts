import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
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

    login(user:User){
        let loginUrl = this.apiUrl+"/login";
        return this.http.post(loginUrl , user);
    }

    getAccountDetailsByCustomerId(customerId: Number) {
        let url = this.apiUrl+"/findByCustomerId?customerId="+customerId;
        return this.http.get(url,{headers : {
            "Authorization" : "Bearer "+ localStorage.getItem('token')
          }});
    }
   
}