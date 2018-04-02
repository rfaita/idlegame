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

  private userId: String;
  private nickName: String;

  constructor(private keycloakService: KeycloakService,
    private chatService: ChatService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {
    this.userId = this.keycloakService.getKeycloakInstance().subject;
    this.nickName = this.keycloakService.getUsername();

    this.subscribeMonitor = this.chatService.subscribeMonitor().subscribe(state => {
      this.state = state;
    });

    this.subscribePrivateErrorMessages = this.chatService.subscribePrivateErrorMessages(this.userId).subscribe(env => {
      this.snotifyService.error(env.errors[0].toString(), '', notificationConfig());
    });

    this.chatService.findAllOldPrivateMessages().subscribe(messages => {
      messages.forEach(message => this.handleMessage(message));
      this.subscribePrivateMessages = this.chatService.subscribePrivateMessages(this.userId).subscribe(message => {
        this.handleMessage(message);
      });
    });

    this.loadChatsJoined();

  }

  private handleMessage(message: Message) {
    let index: String = this.nickName == message.toUserNickName ? message.fromUserId : message.toUserId;

    if (!this.privateMessages.has(index)) {
      this.privateMessages.set(index, []);
      this.privateMessagesKeys.push(index);
    }
    this.privateMessages.get(index).push(message);


  }

  private loadChatsJoined() {
    this.chatService.subscribeChatList().subscribe(chatsJoined => {
      this.chatsJoined = chatsJoined;
      let chatJoinedGlobal: ChatJoined[] = chatsJoined.filter(chatJoined => chatJoined.chatRoomId === "global")
      if (chatJoinedGlobal.length > 0) {
        this.openChat(chatJoinedGlobal[0]);
      }
    });
  }

  public openChat(chatJoined: ChatJoined) {
    this.chatJoined = chatJoined;
    this.chatUsers = [];
    this.chatMessages = [];

    this.chatService.findAllChatRoomUsers(chatJoined.chatRoomId).subscribe(chatUsers => {
      this.chatUsers = chatUsers;
      if (this.subscribeChatUsers != null) {
        this.subscribeChatUsers.unsubscribe();
      }
      this.subscribeChatUsers = this.chatService.subscribeChatUsers(chatJoined.chatRoomId).subscribe(chatUsers => {
        this.chatUsers = chatUsers;
      });
    });

    this.chatService.findAllChatRoomMessages(chatJoined.chatRoomId).subscribe(messages => {
      this.chatMessages = messages;
      if (this.subscribeChatMessage != null) {
        this.subscribeChatMessage.unsubscribe();
      }
      this.subscribeChatMessage = this.chatService.subscribeChatMessage(chatJoined.chatRoomId).subscribe(message => {
        this.chatMessages.push(message);
      });
    });
  }

  public openPrivateChat(user: ChatRoomUser) {
    if (!this.privateMessages.has(user.userId)) {
      this.privateMessages.set(user.userId, []);
      this.privateMessagesKeys.push(user.userId);

      let message: Message = new Message();

      message.fromUserId = this.userId;
      message.fromUserNickName = this.nickName;
      message.toUserId = user.userId;
      message.toUserNickName = user.userNickName;
      message.text = null;

      this.privateMessages.get(user.userId).push(message);
    }
  }

  public sendChatMessage() {
    if (this.text != null && this.text != "") {
      let message: Message = new Message();
      message.text = this.text;
      this.chatService.sendChatMessage(this.chatJoined.chatRoomId, message);
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
        chatJoined.chatRoomId = this.chatToJoin;

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
    this.chatService.leaveChat(this.chatJoined.chatRoomId).subscribe(env => {
      this.chatsJoined = this.chatsJoined.filter(chatJoined => chatJoined.chatRoomId != this.chatJoined.chatRoomId);
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
