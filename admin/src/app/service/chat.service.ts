import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { StompService } from '@stomp/ng2-stompjs';

import { ChatRoomUser } from '../model/chatRoomUser';
import { ChatJoined } from '../model/chatJoined';
import { Message } from '../model/message';
import { ChatRoom } from '../model/chatRoom';
import { KeycloakService } from 'keycloak-angular';
import { ChatStompConfig } from '../utils/chatStompConfigFactory';


@Injectable()
export class ChatService extends StompService {

    constructor(config: ChatStompConfig,
        private http: HttpClient) {
        super(config)
    }

    subscribeMonitor() {
        return this.state;
    }

    subscribePrivateMessages(user: String): Observable<Message> {
        return this.subscribe("/queue/" + user + "#messages.private").map(message => JSON.parse(message.body));
    }

    subscribePrivateErrorMessages(user: String): Observable<Envelope<void>> {
        return this.subscribe("/queue/" + user + "#messages.private.error").map(message => JSON.parse(message.body));
    }

    subscribeChatList(): Observable<ChatJoined[]> {
        return this.subscribe("/chat/findAllChatsJoined").map(message => JSON.parse(message.body));
    }

    subscribeChatMessage(chat: String): Observable<Message> {
        return this.subscribe("/topic/" + chat + "#messages.public").map(message => JSON.parse(message.body));
    }

    subscribeChatUsers(chat: String): Observable<ChatRoomUser[]> {
        return this.subscribe("/topic/" + chat + "#users").map(message => JSON.parse(message.body));
    }

    findAllChatRoomUsers(chat: String): Observable<ChatRoomUser[]> {
        return this.subscribe("/chat/findAllChatRoomUsers/" + chat).map(message => JSON.parse(message.body));
    }

    findAllChatRoomMessages(chat: String): Observable<Message[]> {
        return this.subscribe("/chat/findAllChatRoomMessages/" + chat).map(message => JSON.parse(message.body));
    }

    findAllOldPrivateMessages(): Observable<Message[]> {
        return this.subscribe("/chat/findAllOldPrivateMessages").map(message => JSON.parse(message.body));
    }

    sendChatMessage(chat: String, message: Message) {
        this.publish("/chat/sendChatMessage/" + chat, JSON.stringify(message));

    }

    sendPrivateMessage(chat: String, message: Message) {
        this.publish("/chat/sendPrivateMessage/" + chat, JSON.stringify(message));
    }

    createChat(chatRoom: ChatRoom): Observable<Envelope<ChatRoom>> {
        return <Observable<Envelope<ChatRoom>>>this.http.post(environment.API_BASE_URL + "chat/create", chatRoom);
    }

    joinChat(chat: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.post(environment.API_BASE_URL + "chat/join/" + chat, null);
    }

    leaveChat(chat: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.delete(environment.API_BASE_URL + "chat/leave/" + chat);

    }





}



