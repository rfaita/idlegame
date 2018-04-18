import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { HeroTypesComponent } from './herotypes/herotypes.component';
import { HeroTypeComponent } from './herotype/herotype.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroComponent } from './hero/hero.component';
import { BattleComponent } from './battle/battle.component';
import { ChatComponent } from './chat/chat.component';
import { FriendsComponent } from './friends/friends.component';
import { PvpComponent } from './pvp/pvp.component';
import { GuildComponent } from './guild/guild.component';
import { InventoryComponent } from './inventory/inventory.component';
import { ShopComponent } from './shop/shop.component';

const routes: Routes = [
  { path: 'shop', component: ShopComponent },
  { path: 'guild', component: GuildComponent },
  { path: 'friends', component: FriendsComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'pvp', component: PvpComponent },
  { path: 'battle', component: BattleComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'hero/:id', component: HeroComponent },
  { path: 'heroTypes', component: HeroTypesComponent },
  { path: 'heroType/:id', component: HeroTypeComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: '', redirectTo: 'friends', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
