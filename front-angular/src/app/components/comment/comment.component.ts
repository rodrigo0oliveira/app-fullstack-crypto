import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-comment',
  imports: [],
  templateUrl: './comment.component.html',
})
export class CommentComponent {

  @Input()
  content:string = '';

  @Input()
  userName:string = '';

  @Input()
  date:Date = new Date();



}
