import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../entities/Article';
import { MenuBarComponent } from '../../components/menu-bar/menu-bar.component';
import { FormsModule } from '@angular/forms';
import { CommentRequired } from '../../entities/CommentRequired';
import { TokenService } from '../../services/token.service';
import { ToastrService } from 'ngx-toastr';
import { UtilsService } from '../../services/utils.service';
import { CommentService } from '../../services/comment.service';
import { HttpResponse } from '@angular/common/http';
import { CommentResponse } from '../../entities/CommentResponse';


@Component({
  selector: 'app-content-component',
  imports: [MenuBarComponent,FormsModule],
  templateUrl: './content-component.component.html',
  styleUrl: './content-component.component.css'
})

export class ContentComponent implements OnInit {

  private id:string|null = null;
  photo:string = "";
  title:string = ""
  description = "";

  comment:string = '';



  article:Article|any = {
    id:"",
    title:"",
    description:"",
    link_img:""
  };

  commentRequired:CommentRequired = {
    idArticle:"",
    content:"",
    userName:""
  }

  constructor(private route :ActivatedRoute,
    private articleService:ArticleService,
    private tokenService:TokenService,
    private toastService:ToastrService,
    private utilsService:UtilsService,
    private commentService:CommentService){}

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

  submit():void{
    
    if(this.tokenService.getToken()!=null){
      this.commentRequired.content = this.comment;
      this.commentRequired.idArticle = this.id;
      this.commentRequired.userName = this.tokenService.getUserName();

      this.commentService.createComment(this.commentRequired).subscribe({
        next:(response:HttpResponse<CommentResponse>)=>{
          const body = response.body?.message!;
          if(response.status!=201){
            this.toastService.error(body);
          }
          else{
            this.toastService.success(body);
          }
        },
        error:error=>{
          console.log(error);
          this.toastService.error("Ocorreu um erro inesperado!Por favor tente novamente.");
        }
      })

    }
    else{
      this.toastService.error("Para comentar é necessário estar autenticado! Você será redirecionado para a página de login. ");
      this.utilsService.setPage('/login');
    }
    
  }

  
}

