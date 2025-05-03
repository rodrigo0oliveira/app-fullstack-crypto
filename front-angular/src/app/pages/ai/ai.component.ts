import { Component } from '@angular/core';
import { MenuBarComponent } from "../../components/menu-bar/menu-bar.component";
import { AiService } from '../../services/ai.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ai',
  imports: [MenuBarComponent,CommonModule,FormsModule],
  templateUrl: './ai.component.html',
})
export class AiComponent {

  aiResponse:string = '';

  aiRequest:string = '';

  constructor(private aiService:AiService){

  }

  sendQuestionToAi():void{
    this.aiService.sendQuestionToAi(this.aiRequest).subscribe({
      next:res=>{
        console.log("success!");
        this.aiResponse = res.message;
      },
      error:erro => {
        console.log(erro);
      }
    })
  }

}
