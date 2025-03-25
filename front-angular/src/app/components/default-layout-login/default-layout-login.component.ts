import { Component, INJECTOR, Input } from '@angular/core';


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

}
