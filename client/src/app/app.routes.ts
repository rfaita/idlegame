import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { ShopComponent } from './shop/shop.component';
import { HeroComponent } from './hero/hero.component';


export const routes: Routes = [
    { path: 'heroes', component: HeroesComponent },
    { path: 'hero/:id', component: HeroComponent },
    { path: 'shop', component: ShopComponent }
    
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);