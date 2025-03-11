import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ContentComponent } from './pages/content-component/content-component.component';

export const routes: Routes = [
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"content/:id",
        component:ContentComponent
    }
];
