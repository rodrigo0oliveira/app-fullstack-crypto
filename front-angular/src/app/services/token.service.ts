import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  private TOKEN_NAME:string = "ACESS_TOKEN";

  private USER_NAME:string = "user-name";

  public setToken(token:string):void{
    localStorage.setItem(this.TOKEN_NAME,token);
  }

  public setUserName(userName:string):void{
    localStorage.setItem(this.USER_NAME,userName);
  }

  public getToken():string|null{
    return localStorage.getItem(this.TOKEN_NAME);
  }

  public getUserName():string|null{
    return localStorage.getItem(this.USER_NAME);
  }

  public removeToken():void{
    localStorage.removeItem(this.TOKEN_NAME);
    localStorage.removeItem(this.USER_NAME);
  }
}
