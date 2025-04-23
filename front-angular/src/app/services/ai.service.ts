import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../enviroments/enviroment-dev';
import { Observable } from 'rxjs';
import { MessageDto } from '../entities/MessageDto';

@Injectable({
  providedIn: 'root'
})
export class AiService {

  private baseUrl = '';

  constructor(private httpClient:HttpClient) {
    this.baseUrl = enviroment.blogAPI + "/ai";
  }

  sendQuestionToAi(message:string):Observable<MessageDto>{
    return this.httpClient.post<MessageDto>(this.baseUrl,message);
  }
}
