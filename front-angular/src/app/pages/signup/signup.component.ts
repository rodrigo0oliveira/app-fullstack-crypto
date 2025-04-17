import { Component } from '@angular/core';
import { DefaultLayoutLoginComponent } from '../../components/default-layout-login/default-layout-login.component';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from '../../services/signup.service';
import { User } from '../../models/User';
import { HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { UtilsService } from '../../services/utils.service';

@Component({
  selector: 'app-signup',
  imports: [DefaultLayoutLoginComponent,PrimaryInputComponent,ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm!:FormGroup;

  user:User = {
    userName:"",
    email:"",
    password:""
  }

  constructor(private router:Router,
    private signupService:SignupService,
    private toastService:ToastrService,
    private utilsService:UtilsService){
    this.signupForm = new FormGroup({
      username:new FormControl("",[Validators.required,Validators.minLength(5)]),
      email:new FormControl("",[Validators.email,Validators.required]),
      password:new FormControl("",[Validators.required,Validators.minLength(8)]),
      confirmPassword:new FormControl("",[Validators.required,Validators.minLength(8)])
    })
  }

  submit():void{

    const userName:string = this.signupForm.value.username;
    const email:string = this.signupForm.value.email;
    const password:string = this.signupForm.value.password;
    const confirmPassword:string = this.signupForm.value.confirmPassword;

    if(!this.matchPassword(password,confirmPassword)){
      this.toastService.error("As duas senhas precisam ser iguais!");
      return;
    }

    this.user ={
      userName:userName,
      email:email,
      password:password
    }

    this.signupService.signup(this.user).subscribe({
      next:(response:HttpResponse<any>)=>{
        const status:number = response.status;
        console.log(response.status)
        if(status!=201){
          this.toastService.error(response.body);
          return;
        }else{
          this.toastService.success(response.body);
          this.utilsService.setPage('/login');
        }
        
      },
      error:(erro)=>{
        console.log(erro);
        this.toastService.error(erro.error);
      }

    })
  }

  navigate(route:string):void{
    this.router.navigate([route]);
  }

  matchPassword(password:string,confirmPassword:string):boolean{
    if(password===confirmPassword){
      return true;
    }
    return false;
  }

}
