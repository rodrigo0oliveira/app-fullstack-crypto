import { Component, OnInit } from '@angular/core';
import { MenuTitleComponent } from '../../components/menu-title/menu-title.component';
import { BigCardComponent } from '../../components/big-card/big-card.component';
import { MenuBarComponent } from '../../components/menu-bar/menu-bar.component';
import { Article } from '../../entities/Article';
import { ArticleService } from '../../services/article.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [MenuTitleComponent, BigCardComponent,MenuBarComponent,CommonModule],
  templateUrl: './home.component.html',

})
export class HomeComponent implements OnInit{
  

  articles:Article[] = [];

  constructor(private articleService:ArticleService){

  }

  ngOnInit(): void {
    this.getAllArticles();
  }

  getAllArticles():void{
    this.articleService.getAllArticle().subscribe({
      next:(response)=>{
        this.articles = response;
      }
    })
  }


}
