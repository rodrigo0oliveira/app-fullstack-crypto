import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../enviroments/enviroment-dev';
import { User } from '../models/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private baseUrl:string = "";

  private userName:string = "";

  private email:string = "";

  private password:string = "";

  

  constructor(private httpClient:HttpClient) {
    this.baseUrl = enviroment.blogAPI + "/auth/signup"
  }

  signup(user:User):Observable<HttpResponse<any>>{

    return this.httpClient
      .post<any>
      (this.baseUrl
        ,user
        ,{observe:'response',responseType:'text' as 'json'});
  }


}
