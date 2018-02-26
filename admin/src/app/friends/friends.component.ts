import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { PlayerService } from '../service/player.service';
import { showNotification } from '../utils/helper';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { FriendService } from '../service/friend.service';
import { Friend } from '../model/friend';
import { Mail } from '../model/mail';
import { MailService } from '../service/mail.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  public player: String;
  public friends: Friend[];

  public toUser: String;
  public message: String;

  constructor(private friendService: FriendService,
    private playerService: PlayerService,
    private mailService: MailService,
    private keycloakService: KeycloakService) { }

  ngOnInit() {
    this.loadFriends();
  }

  loadFriends() {
    this.friendService.friends().subscribe(env => {
      this.friends = env.data;
    });
  }

  addFriend() {
    this.playerService.findByName(this.player).subscribe(env => {

      this.friendService.sendFriendRequest(env.data.linkedUser).subscribe(env => {
        this.loadFriends();
      });
    });
  }

  acceptFriend(id: String) {

    this.friendService.acceptFriendRequest(id).subscribe(env => {
      this.loadFriends();
    });

  }

  removeFriend(id: String) {

    this.friendService.removeFriend(id).subscribe(env => {
      this.loadFriends();
    });

  }

  sendPrivateMail() {
    let mail: Mail = new Mail();
    mail.text = this.message;
    mail.toUser = this.toUser;

    this.mailService.sendPrivateMail(mail).subscribe(env => {
      this.message = "";
      this.toUser = "";
    });
  }

  public keyDownAddFriend(event: any) {
    if (event.keyCode == 13) {
      this.addFriend();
    }
  }



}

