import { Injectable } from '@angular/core';
import { enviroment } from '../enviroments/enviroment-dev';
import { LoginRequired } from '../entities/LoginRequired';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { TokenResponse } from '../entities/TokenResponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl:string = enviroment.blogAPI + "/auth/login";

  constructor(private httpClient:HttpClient) { 

  }


  login(loginRequired:LoginRequired):Observable<HttpResponse<TokenResponse>>{
    return this.httpClient.post<TokenResponse>(this.baseUrl,
      loginRequired,{observe:'response'})
  }
}
