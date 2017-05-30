import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { Routes, RouterModule, Router } from '@angular/router';

import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroesService } from './service/heroes.service';
import { KeycloakService } from './service/keycloak.service';
import { KEYCLOAK_HTTP_PROVIDER } from './service/keycloak.http';
import { ShopComponent } from './shop/shop.component';
import { ShopService } from './service/shop.service';
import { HeroComponent } from './hero/hero.component';
import { CampaignComponent } from './campaign/campaign.component';
import { FormationService } from './service/formation.service';
import { BattleService } from './service/battle.service';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    ShopComponent,
    HeroComponent,
    CampaignComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing
  ],
  providers: [
    KeycloakService,
    HeroesService,
    ShopService,
    FormationService,
    BattleService,
    KEYCLOAK_HTTP_PROVIDER
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
