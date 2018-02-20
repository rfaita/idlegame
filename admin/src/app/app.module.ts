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
import { ChatService } from './service/chat.service';
import { BattleComponent } from './battle/battle.component';
import { BattleService } from './service/battle.service';
import { FormationService } from './service/formation.service';
import { ChatComponent } from './chat/chat.component';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';


const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://localhost:8083/ws?access_token=eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSZUZsZTRiTlJmY1FxWlJkX0RScEF3TzYtSnVIS3NWYkxOOVpDUXhWVlJNIn0.eyJqdGkiOiI4MjRiMjZjMS04NGUwLTQ4NTMtODVjNi03Njg2ZTBlNTdhODkiLCJleHAiOjE1MTkxODQ5NjQsIm5iZiI6MCwiaWF0IjoxNTE5MTQ4OTY3LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvaWRsZXJlYWxtIiwiYXVkIjoiaWRsZWdhbWUiLCJzdWIiOiJjNzIzZmM1MC02MzlkLTRlZWMtYTQ0OS1jZWJkYTcwNTk5YzkiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJpZGxlZ2FtZSIsIm5vbmNlIjoiNmQwZjkyOTktYjE4MS00YWFiLThlZjktNWZiNjgxMDc3ZGRhIiwiYXV0aF90aW1lIjoxNTE5MTQ4OTY0LCJzZXNzaW9uX3N0YXRlIjoiY2U1MjM2NzctNjE1Yy00ZWNkLThjNjUtNTY0NWJlNWFlNTYxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJuYW1lIjoiUkFGQUVMIEZBSVRBIiwiaWQiOiJjNzIzZmM1MC02MzlkLTRlZWMtYTQ0OS1jZWJkYTcwNTk5YzkiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJyZmFpdGEiLCJnaXZlbl9uYW1lIjoiUkFGQUVMIiwibG9jYWxlIjoicHQtQlIiLCJmYW1pbHlfbmFtZSI6IkZBSVRBIiwiZW1haWwiOiJyZmFpdGFAZ21haWwuY29tIn0.chpEvZqcr6s5t1fix104ShyZblJZfahFr3iKeRmETQh0eW31OjdPsbz18Ru52ELFp_IGm9YhZ3WQvXmQ35n7eqV_9a1D7Am2odjWzHsTbK-daUGlyTCEJLMVc9mWPi21lIJkHys0YM0gs1w3Uawabhmm5ju0ABQoRc6E7ys3BgOPDj-bUDHlv6qkyeRJ7JgV3djVqXgkYRM1XHJG5vjI9qZrmBXHJPakEZ0grqMIEjpiRKtb3p7ziqzDmaUsFNuE9GY2Z2PJAFlP8B1xq7kdgIGjq0ySgFsvhq5IVuboxKhVUK_x-4n3LKifnxHplDFTEVmmVSAzpTcNT6rqX6ELHg',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserProfileComponent,
    TableListComponent,
    TypographyComponent,
    IconsComponent,
    NotificationsComponent,
    UpgradeComponent,
    HeroTypesComponent, HeroTypeComponent,
    HeroesComponent, HeroComponent,
    ActionEffectComponent,
    BuffEffectComponent,
    BattleComponent,
    ChatComponent

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
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    },
    HeroService,
    HeroTypeService,
    HeroTypeTypesService,
    HeroTypesService,
    ActionEffectTypesService,
    PlayerService,
    BattleService,
    FormationService,
    ChatService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
