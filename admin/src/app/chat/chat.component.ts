import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { showNotification } from '../utils/helper';
import { KeycloakService } from 'keycloak-angular';
import { ChatService } from '../service/chat.service';
import { KeycloakProfile } from 'keycloak-js';
import { ChatJoined } from '../model/chatJoined';
import { Observable } from 'rxjs/Observable';
import { Envelope } from '../model/envelope';
import { ChatRoomUser } from '../model/chatRoomUser';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  private sub: any;
  private chatJoined: ChatJoined;
  private chatsJoined: Observable<ChatJoined[]>;
  private chatUsers: ChatRoomUser[] = [];
  private chatMessages: any[] = [];


  constructor(private keycloakService: KeycloakService,
    private chatService: ChatService) { }

  ngOnInit() {
    let subject = this.keycloakService.getKeycloakInstance().subject;

    this.chatService.subscribePrivateErrorMessages(subject).subscribe(message => {
      let env: Envelope<void> = JSON.parse(message.body);
      showNotification("danger", env.errors[0]);
    });

    this.chatService.subscribePrivateMessages(subject).subscribe(message => {
      showNotification("info", message.body);
    });

    this.chatsJoined = this.chatService.subscribeChatList();
  }

  public joinChat(chatJoined: ChatJoined) {
    this.chatJoined = chatJoined;
    this.chatUsers = [];
    this.chatMessages = [];

    this.chatService.findAllChatRoomUsers(chatJoined.chatRoom).subscribe(chatUsers => {
      this.chatUsers = chatUsers;
      this.chatService.subscribeChatUsers(chatJoined.chatRoom).subscribe(chatUsers => {
        this.chatUsers = chatUsers;
      });
    });

    this.chatService.findAllChatRoomMessages(chatJoined.chatRoom).subscribe(messages => {
      this.chatMessages = messages;
      this.chatService.subscribeChatMessage(chatJoined.chatRoom).subscribe(message => {
        this.chatMessages.push(message);
      });
    });
  }

  ngOnDestroy() {
    //this.sub.unsubscribe();
  }


}
