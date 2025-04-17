import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private router:Router) { }

  setPage(page:string):void{
    setTimeout(()=>{
      this.router.navigate([page])
    },5000   
    )
  }
}
