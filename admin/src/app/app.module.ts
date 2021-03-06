import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { SnotifyModule, ToastDefaults, SnotifyService } from 'ng-snotify';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { initializer } from './utils/initializer';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';

import { errorInterceptorProvider } from './utils/error.interceptor';

import { AppComponent } from './app.component';
import { HeroTypesComponent } from './herotypes/herotypes.component';
import { HeroTypeService } from './service/herotype.service';
import { HeroTypeComponent } from './herotype/herotype.component';
import { HeroTypeTypesService } from './service/herotypetypes.service';
import { ActionEffectTypesService } from './service/actioneffecttypes.service';
import { ActionEffectComponent } from './actionEffect/actionEffect.component';
import { BuffEffectComponent } from './buffEffect/buffEffect.component';
import { HeroService } from './service/hero.service';
import { HeroesComponent } from './heroes/heroes.component';
import { UserService } from './service/user.service';
import { HeroComponent } from './hero/hero.component';
import { HeroTypesService } from './service/herotypes.service';
import { ChatService } from './service/chat.service';
import { BattleComponent } from './battle/battle.component';
import { BattleService } from './service/battle.service';
import { FormationService } from './service/formation.service';
import { ChatComponent } from './chat/chat.component';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';
import { chatStompConfigFactory, ChatStompConfig } from './utils/chatStompConfigFactory';
import { AvatarModule } from 'ngx-avatar';
import { ChatPmComponent } from './chatpm/chatpm.component';
import { MailService } from './service/mail.service';
import { mailStompConfigFactory, MailStompConfig } from './utils/mailStompConfigFactory';
import { FriendService } from './service/friend.service';
import { FriendsComponent } from './friends/friends.component';
import { PvpService } from './service/pvp.service';
import { PvpComponent } from './pvp/pvp.component';
import { UserResourceService } from './service/userresource.service';
import { UserResourceStompConfig, userResourceStompConfigFactory } from './utils/playerStompConfigFactory';
import { KeysPipe } from './utils/pipe/keys.pipe';
import { Guild } from './model/guild';
import { GuildService } from './service/guild.service';
import { GuildComponent } from './guild/guild.component';
import { GuildMemberService } from './service/guildmember.service';
import { InventoryComponent } from './inventory/inventory.component';
import { InventoryService } from './service/inventory.service';
import { ShopService } from './service/shop.service';
import { ShopComponent } from './shop/shop.component';
import { BattleHeroService } from './service/battleHero.service';
import { BattleHero } from './model/battleHero';
import { HeroDetailComponent } from './heroDetail/heroDetail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeroTypesComponent, HeroTypeComponent,
    HeroesComponent, HeroComponent,
    ActionEffectComponent,
    BuffEffectComponent,
    BattleComponent,
    ChatComponent, ChatPmComponent,
    FriendsComponent,
    PvpComponent,
    GuildComponent,
    InventoryComponent,
    ShopComponent,
    HeroDetailComponent,
    KeysPipe
  ],
  imports: [
    AvatarModule,
    KeycloakAngularModule,
    SnotifyModule,
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
    {
      provide: ChatStompConfig,
      useFactory: chatStompConfigFactory,
      deps: [KeycloakService]
    },
    {
      provide: MailStompConfig,
      useFactory: mailStompConfigFactory,
      deps: [KeycloakService]
    },
    {
      provide: UserResourceStompConfig,
      useFactory: userResourceStompConfigFactory,
      deps: [KeycloakService]
    },
    {
      provide: 'SnotifyToastConfig',
      useValue: ToastDefaults
    },
    errorInterceptorProvider,
    SnotifyService,
    ChatService,
    MailService,
    HeroService,
    HeroTypeService,
    HeroTypeTypesService,
    HeroTypesService,
    ActionEffectTypesService,
    UserService,
    BattleService,
    FormationService,
    ChatService,
    MailService,
    FriendService,
    PvpService,
    UserResourceService,
    GuildService,
    GuildMemberService,
    InventoryService,
    ShopService,
    BattleHeroService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
