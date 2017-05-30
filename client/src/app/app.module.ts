import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { Routes, RouterModule, Router } from '@angular/router';

import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroesService } from './heroes/heroes.service';
import { KeycloakService } from './service/keycloak.service';
import { KEYCLOAK_HTTP_PROVIDER } from './service/keycloak.http';
import { ShopComponent } from './shop/shop.component';
import { ShopService } from './shop/shop.service';
import { HeroComponent } from './hero/hero.component';
import { HeroService } from './hero/hero.service';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    ShopComponent,
    HeroComponent
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
    HeroService,
    ShopService,
    KEYCLOAK_HTTP_PROVIDER
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
