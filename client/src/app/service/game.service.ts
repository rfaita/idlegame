import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { WebSocketService } from './websocket.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { environment } from '../../environments/environment';
import { SocketMessage } from '../model/socketMessage';


@Injectable()
export class GameService {

    public authToken: string;
    private subject: Subject<SocketMessage>;

    constructor(private wsService: WebSocketService) {
    }

    public getSubject(): Subject<SocketMessage> {
        if (!this.subject) {
            this.subject = this.wsService.connect(environment.WS_BASE_URL, this.authToken);
        }
        return this.subject;
    }

    public send(o: SocketMessage) {
        this.wsService.subject.next(JSON.stringify(o));
    }


} 