import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../models/Article';
import { MenuBarComponent } from '../../components/menu-bar/menu-bar.component';


@Component({
  selector: 'app-content-component',
  imports: [MenuBarComponent],
  templateUrl: './content-component.component.html',
  styleUrl: './content-component.component.css'
})

export class ContentComponent implements OnInit {

  private id:string|null = null;
  photo:string = "";
  title:string = ""
  description = "";

  article:Article|any = {
    id:"",
    title:"",
    description:"",
    link_img:""
  };

  constructor(private route :ActivatedRoute,private articleService:ArticleService){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(value => 
      this.id = value.get("id")
    ) 

    if(this.id){
      this.setValuesToComponent(this.id);
    }
  }

  setValuesToComponent(id:string):void{
    this.article = this.articleService.getArticleById(id).subscribe(
      {
        next:res=>{
          id=res.id,
          this.title=res.title,
          this.description=res.description,
          this.photo=res.linkImg
        },
        error : err => console.log(err)
      }
    );

    console.log(this.article);
  }

  
}

