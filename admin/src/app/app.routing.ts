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

const routes: Routes = [
  { path: 'friends', component: FriendsComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'battle', component: BattleComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'hero/:id', component: HeroComponent },
  { path: 'heroTypes', component: HeroTypesComponent },
  { path: 'heroType/:id', component: HeroTypeComponent },
  { path: '', redirectTo: 'heroes', pathMatch: 'full' }
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
