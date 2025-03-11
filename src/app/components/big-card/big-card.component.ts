import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-big-card',
  imports: [RouterModule],
  templateUrl: './big-card.component.html',
  styleUrl: './big-card.component.css'
})
export class BigCardComponent {

  @Input()
  photo:string = "";

  @Input()
  title:string = "";

  @Input()
  description = "";

  @Input()
  id:string = "0"

}
