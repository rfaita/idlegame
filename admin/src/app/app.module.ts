import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { initializer } from './utils/initializer';

import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { TableListComponent } from './table-list/table-list.component';
import { TypographyComponent } from './typography/typography.component';
import { IconsComponent } from './icons/icons.component';
import { MapsComponent } from './maps/maps.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { UpgradeComponent } from './upgrade/upgrade.component';
import { HeroTypesComponent } from './herotypes/herotypes.component';
import { HeroTypeService } from './service/herotype.service';
import { HeroTypeComponent } from './herotype/herotype.component';
import { HeroTypeTypesService } from './service/herotypetypes.service';
import { ActionEffectTypesService } from './service/actioneffecttypes.service';
import { ActionEffectComponent } from './actionEffect/actionEffect.component';
import { BuffEffectComponent } from './buffEffect/buffEffect.component';
import { HeroService } from './service/hero.service';
import { HeroesComponent } from './heroes/heroes.component';
import { PlayerService } from './service/player.service';
import { HeroComponent } from './hero/hero.component';
import { HeroTypesService } from './service/herotypes.service';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserProfileComponent,
    TableListComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    UpgradeComponent,
    HeroTypesComponent, HeroTypeComponent,
    HeroesComponent, HeroComponent,
    ActionEffectComponent,
    BuffEffectComponent

  ],
  imports: [
    KeycloakAngularModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    },
    HeroService,
    HeroTypeService,
    HeroTypeTypesService,
    HeroTypesService,
    ActionEffectTypesService,
    PlayerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
