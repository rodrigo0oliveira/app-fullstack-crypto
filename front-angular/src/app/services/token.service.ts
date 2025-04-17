import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  private TOKEN_NAME:string = "ACESS_TOKEN";

  public setToken(token:string):void{
    localStorage.setItem(this.TOKEN_NAME,token);
  }

  public getToken():string|null{
    return localStorage.getItem(this.TOKEN_NAME);
  }

  public removeToken():void{
    localStorage.removeItem(this.TOKEN_NAME);
  }
}
