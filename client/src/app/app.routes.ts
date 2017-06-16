import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { ShopComponent } from './shop/shop.component';
import { HeroComponent } from './hero/hero.component';
import { CampaignComponent } from './campaign/campaign.component';
import { PvpComponent } from './pvp/pvp.component';


export const routes: Routes = [
    { path: 'campaign', component: CampaignComponent },
    { path: 'heroes', component: HeroesComponent },
    { path: 'hero/:id', component: HeroComponent },
    { path: 'shop', component: ShopComponent },
    { path: 'pvp', component: PvpComponent }
    
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);