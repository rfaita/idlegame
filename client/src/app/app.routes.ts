import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';


export const routes: Routes = [

    { path: 'heroes', component: HeroesComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);