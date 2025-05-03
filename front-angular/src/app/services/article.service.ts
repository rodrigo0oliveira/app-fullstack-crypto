import { Injectable } from '@angular/core';
import { enviroment } from '../enviroments/enviroment-dev';
import { Observable } from 'rxjs';
import { Article } from '../entities/Article';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ArticleService {

  private baseURL:string = "";

  private articleData:Article|any = "";

  constructor(private http:HttpClient) {
    this.baseURL = enviroment.blogAPI+"/article";
  }

  getArticleById(id:string):Observable<Article>{
    this.articleData = this.http.get<Article>(this.baseURL+"?id="+id);
    
    return this.articleData;
  }

  getAllArticle():Observable<Article[]>{
    return this.http.get<Article[]>(this.baseURL);
  }



}
