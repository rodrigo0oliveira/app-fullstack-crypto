import { Component } from '@angular/core';
import { DefaultLayoutLoginComponent } from '../../components/default-layout-login/default-layout-login.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { LoginRequired } from '../../models/LoginRequired';
import { LoginService } from '../../services/login.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [DefaultLayoutLoginComponent,ReactiveFormsModule,PrimaryInputComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginRequired:LoginRequired = {
    userName:"",
    password:""
  }

  loginForm!:FormGroup;

  constructor(private router:Router,private loginService:LoginService){
    this.loginForm = new FormGroup({
      username: new FormControl("",[Validators.required,Validators.email]),
      password: new FormControl("",[Validators.required,Validators.minLength(8)])
    })
  }

  submit(){
    const username:string = this.loginForm.value.username;
    const password:string = this.loginForm.value.password;

    this.loginRequired = {
      userName:username,
      password:password
    }

    this.loginService.login(this.loginRequired).subscribe({
      next:(response:HttpResponse<any>)=>{
        const status = response.status;
        if(status!=200){
          console.log("Erro ao realizar o login");
        }else{
          console.log(response.body)
        }
      }
    })



  }

  navigate(route:string){
    this.router.navigate([route]);
  }

}
