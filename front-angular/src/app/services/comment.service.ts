import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { CommentRequired } from '../entities/CommentRequired';
import { enviroment } from '../enviroments/enviroment-dev';
import { Observable } from 'rxjs';
import { CommentResponse } from '../entities/CommentResponse';
import { CommentDto } from '../entities/CommentDto';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl:string = "";

  commentRequired:CommentRequired = {
    content:'',
    idArticle:'',
    userName:''
  }
   

  constructor(private httpClient:HttpClient,private tokenService:TokenService) {
    this.baseUrl = enviroment.blogAPI+"/comments";
   }

  createComment(commentRequired: CommentRequired): Observable<HttpResponse<CommentResponse>> {
    return this.httpClient.post<CommentResponse>(this.baseUrl, commentRequired,
       {observe: 'response',});
  }

  findCommentsByArticleId(id:number):Observable<CommentDto[]> {
    return this.httpClient.get<CommentDto[]>(this.baseUrl+"?articleId="+id);
  }



  
  
}
