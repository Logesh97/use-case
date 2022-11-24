import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { User } from "./user.model";

const apiGateway = environment.apiGateway;
@Injectable({
    providedIn:'root'
})
export class UserService{
    apiUrl:string = apiGateway+"customer";
    constructor(private http:HttpClient){
    }
    
    
    signup(user: User) {
        let signUpUrl = this.apiUrl+"/signup";
        return this.http.post(signUpUrl , user);
    }

    login(user:User){
        let loginUrl = this.apiUrl+"/login";
        return this.http.post(loginUrl , user);
    }
   
}