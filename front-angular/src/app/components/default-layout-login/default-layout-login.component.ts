import { Component, EventEmitter, INJECTOR, Input, Output } from '@angular/core';


@Component({
  selector: 'app-default-layout-login',
  imports: [],
  templateUrl: './default-layout-login.component.html',
  styleUrl: './default-layout-login.component.css'
})
export class DefaultLayoutLoginComponent {

  @Input()
  title:string = "";

  @Input()
  primaryBtnText = "";

  @Input()
  secondaryBtnText = "";

  @Input()
  disablePrimaryButton:boolean = true;

  @Output("submit")
  onSubmit = new EventEmitter();

  @Output("navigate")
  onNavigate = new EventEmitter();

  submit(){
    this.onSubmit.emit();
  }

  navigate(){
    this.onNavigate.emit();
  }

}
