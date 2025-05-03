import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { UtilsService } from './utils.service';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private tokenService:TokenService,private utilsService:UtilsService) { }

  logout():void{
    this.tokenService.removeToken();
    this.utilsService.setPage('/login');
  }
}
