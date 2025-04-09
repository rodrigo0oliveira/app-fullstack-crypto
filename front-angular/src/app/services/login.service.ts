import { Injectable } from '@angular/core';
import { enviroment } from '../enviroments/enviroment-dev';
import { LoginRequired } from '../models/LoginRequired';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl:string = enviroment.blogAPI + "/auth/login";

  constructor(private httpClient:HttpClient) { 

  }


  login(loginRequired:LoginRequired):Observable<HttpResponse<any>>{
    return this.httpClient.post<any>(this.baseUrl,loginRequired,{observe:'response'})
  }
}
