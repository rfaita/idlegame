import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { KeycloakService } from 'keycloak-angular';
import { ChatService } from '../service/chat.service';
import { KeycloakProfile } from 'keycloak-js';
import { ChatJoined } from '../model/chatJoined';
import { Observable } from 'rxjs/Observable';
import { Envelope } from '../model/envelope';
import { ChatRoomUser } from '../model/chatRoomUser';
import { Subscription } from 'rxjs/Subscription';
import { Message } from '../model/message';
import { ChatRoom } from '../model/chatRoom';
import { SnotifyService } from 'ng-snotify';
import { notificationConfig } from '../utils/helper';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  public state: Number;
  public text: String;
  public chatToJoin: String;
  public chatToCreate: String;

  public chatJoined: ChatJoined;
  public chatsJoined: ChatJoined[];
  public chatUsers: ChatRoomUser[] = [];
  public chatMessages: any[] = [];

  public privateMessages: Map<String, Message[]> = new Map();
  public privateMessagesKeys: String[] = [];

  private subscribeMonitor: Subscription;
  private subscribeChatUsers: Subscription;
  private subscribeChatMessage: Subscription;
  private subscribePrivateErrorMessages: Subscription;
  private subscribePrivateMessages: Subscription;

  private subject: String;
  private username: String;

  constructor(private keycloakService: KeycloakService,
    private chatService: ChatService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {
    this.subject = this.keycloakService.getKeycloakInstance().subject;
    this.username = this.keycloakService.getUsername();

    this.subscribeMonitor = this.chatService.subscribeMonitor().subscribe(state => {
      this.state = state;
    });

    this.subscribePrivateErrorMessages = this.chatService.subscribePrivateErrorMessages(this.subject).subscribe(env => {
      this.snotifyService.error(env.errors[0].toString(), '', notificationConfig());
    });

    this.chatService.findAllOldPrivateMessages().subscribe(messages => {
      messages.forEach(message => this.handleMessage(message));
      this.subscribePrivateMessages = this.chatService.subscribePrivateMessages(this.subject).subscribe(message => {
        this.handleMessage(message);
      });
    });

    this.loadChatsJoined();

  }

  private handleMessage(message: Message) {
    let index: String = this.username == message.toNickName ? message.fromUser : message.toUser;

    if (!this.privateMessages.has(index)) {
      this.privateMessages.set(index, []);
      this.privateMessagesKeys.push(index);
    }
    this.privateMessages.get(index).push(message);


  }

  private loadChatsJoined() {
    this.chatService.subscribeChatList().subscribe(chatsJoined => {
      this.chatsJoined = chatsJoined;
      let chatJoinedGlobal: ChatJoined[] = chatsJoined.filter(chatJoined => chatJoined.chatRoom === "global")
      if (chatJoinedGlobal.length > 0) {
        this.openChat(chatJoinedGlobal[0]);
      }
    });
  }

  public openChat(chatJoined: ChatJoined) {
    this.chatJoined = chatJoined;
    this.chatUsers = [];
    this.chatMessages = [];

    this.chatService.findAllChatRoomUsers(chatJoined.chatRoom).subscribe(chatUsers => {
      this.chatUsers = chatUsers;
      if (this.subscribeChatUsers != null) {
        this.subscribeChatUsers.unsubscribe();
      }
      this.subscribeChatUsers = this.chatService.subscribeChatUsers(chatJoined.chatRoom).subscribe(chatUsers => {
        this.chatUsers = chatUsers;
      });
    });

    this.chatService.findAllChatRoomMessages(chatJoined.chatRoom).subscribe(messages => {
      this.chatMessages = messages;
      if (this.subscribeChatMessage != null) {
        this.subscribeChatMessage.unsubscribe();
      }
      this.subscribeChatMessage = this.chatService.subscribeChatMessage(chatJoined.chatRoom).subscribe(message => {
        this.chatMessages.push(message);
      });
    });
  }

  public openPrivateChat(user: ChatRoomUser) {
    if (!this.privateMessages.has(user.user)) {
      this.privateMessages.set(user.user, []);
      this.privateMessagesKeys.push(user.user);

      let message: Message = new Message();

      message.fromUser = this.subject;
      message.fromNickName = this.username;
      message.toUser = user.user;
      message.toNickName = user.nickName;
      message.text = null;

      this.privateMessages.get(user.user).push(message);
    }
  }

  public sendChatMessage() {
    if (this.text != null && this.text != "") {
      let message: Message = new Message();
      message.text = this.text;
      this.chatService.sendChatMessage(this.chatJoined.chatRoom, message);
      this.text = "";
    }
  }

  public createChat() {
    if (this.chatToCreate != null && this.chatToCreate != "") {

      let chatRoom: ChatRoom = new ChatRoom();
      chatRoom.name = this.chatToCreate;

      this.chatService.createChat(chatRoom).subscribe(env => {
        this.chatToCreate = "";
        this.snotifyService.info("Chat created.", '', notificationConfig());
        
      });
    }
  }

  public joinChat() {
    if (this.chatToJoin != null && this.chatToJoin != "") {
      this.chatService.joinChat(this.chatToJoin).subscribe(env => {

        let chatJoined: ChatJoined = new ChatJoined();
        chatJoined.chatRoom = this.chatToJoin;

        this.chatsJoined.push(chatJoined);

        this.openChat(chatJoined);

        this.chatToJoin = "";
      });
    }
  }

  public leaveChat() {
    if (this.subscribeChatUsers != null) {
      this.subscribeChatUsers.unsubscribe();
    }
    if (this.subscribeChatMessage != null) {
      this.subscribeChatMessage.unsubscribe();
    }
    this.chatService.leaveChat(this.chatJoined.chatRoom).subscribe(env => {
      this.chatsJoined = this.chatsJoined.filter(chatJoined => chatJoined.chatRoom != this.chatJoined.chatRoom);
      this.chatJoined = null;
      this.chatUsers = [];
      this.chatMessages = [];
    });

  }

  ngOnDestroy() {
    if (this.subscribeMonitor != null) {
      this.subscribeMonitor.unsubscribe();
    }
    if (this.subscribePrivateErrorMessages != null) {
      this.subscribePrivateErrorMessages.unsubscribe();
    }
    if (this.subscribePrivateMessages != null) {
      this.subscribePrivateMessages.unsubscribe();
    }
  }


}
