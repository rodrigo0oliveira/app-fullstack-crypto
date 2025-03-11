import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { dataFakeCryto } from '../../data/datafakeCrypto';

@Component({
  selector: 'app-content-component',
  imports: [],
  templateUrl: './content-component.component.html',
  styleUrl: './content-component.component.css'
})

export class ContentComponent implements OnInit {

  private id:string|null = "";
  photo:string = "";
  title:string = ""
  description = "";

  constructor(private route :ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(value => 
      this.id = value.get("id")
    ) 

    this.setValuesToComponent(this.id);
  }

  setValuesToComponent(id:string|null):void{
    const result = dataFakeCryto.filter(article =>
      article.id === id
    )[0]

    this.photo = result.photo
    this.title = result.title
    this.description = result.description
  }

  
}

