import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ContentComponent } from './pages/content-component/content-component.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AiComponent } from './pages/ai/ai.component';
import { CotacaoComponent } from './pages/cotacao/cotacao.component';

export const routes: Routes = [
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"content/:id",
        component:ContentComponent
    },
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"signup",
        component:SignupComponent
    },
    {
        path:"ai",
        component:AiComponent
    },
    {
        path:"cotações",
        component:CotacaoComponent
    }
];
