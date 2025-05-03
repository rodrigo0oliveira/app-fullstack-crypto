import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TokenService } from '../../services/token.service';
import { CommonModule } from '@angular/common';
import { LogoutService } from '../../services/logout.service';

@Component({
  selector: 'app-menu-bar',
  imports: [RouterModule,CommonModule],
  templateUrl: './menu-bar.component.html',
})
export class MenuBarComponent implements OnInit{

  isLogged:boolean = false;

  constructor(private tokenService:TokenService,private logoutService:LogoutService){
  }

  ngOnInit(): void {
    if(this.tokenService.getToken()!=null){
      this.isLogged = true;
    }
  }

  onLogout():void{
    this.logoutService.logout();
    this.isLogged = false;
  }


}
