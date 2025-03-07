import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ContentComponent } from './components/content-component/content-component.component';

export const routes: Routes = [
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"content",
        component:ContentComponent
    }
];
