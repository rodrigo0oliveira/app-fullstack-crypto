import { Component } from '@angular/core';
import { DefaultLayoutLoginComponent } from '../../components/default-layout-login/default-layout-login.component';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [DefaultLayoutLoginComponent,PrimaryInputComponent,ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm!:FormGroup;

  constructor(){
    this.signupForm = new FormGroup({
      username:new FormControl("",[Validators.required,Validators.minLength(5)]),
      email:new FormControl("",[Validators.email,Validators.required]),
      password:new FormControl("",[Validators.required,Validators.minLength(8)]),
      confirmPassword:new FormControl("",[Validators.required,Validators.minLength(8)])
    })
  }

}
