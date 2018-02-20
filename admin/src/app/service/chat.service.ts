import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';
import { StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { ChatRoomUser } from '../model/chatRoomUser';
import { ChatJoined } from '../model/chatJoined';


@Injectable()
export class ChatService {

    constructor(private stompService: StompService) {

    }

    subscribePrivateMessages(user: String): Observable<Message> {
        return this.stompService.subscribe("/queue/" + user + "#messages.private").map(message => JSON.parse(message.body));
    }

    subscribePrivateErrorMessages(user: String): Observable<Message> {
        return this.stompService.subscribe("/queue/" + user + "#messages.private.error").map(message => JSON.parse(message.body));
    }

    subscribeChatList(): Observable<ChatJoined[]> {
        return this.stompService.subscribe("/chat/findAllChatsJoined").map(message => JSON.parse(message.body));
    }

    subscribeChatMessage(chat:String): Observable<any> {
        return this.stompService.subscribe("/topic/" + chat + "#messages.public").map(message => JSON.parse(message.body));
    }

    subscribeChatUsers(chat:String): Observable<ChatRoomUser[]> {
        return this.stompService.subscribe("/topic/" + chat + "#users.connected").map(message => JSON.parse(message.body));
    }

    findAllChatRoomUsers(chat:String): Observable<ChatRoomUser[]> {
        return this.stompService.subscribe("/chat/findAllChatRoomUsers/" + chat).map(message => JSON.parse(message.body));
    }

    findAllChatRoomMessages(chat:String): Observable<any[]> {
        return this.stompService.subscribe("/chat/findAllChatRoomMessages/" + chat).map(message => JSON.parse(message.body));
    }

}



