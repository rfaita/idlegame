import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { WebSocketService } from './websocket.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { environment } from '../../environments/environment';
import { SocketMessage } from '../model/socketMessage';


@Injectable()
export class GameService {

    public subject: Subject<SocketMessage>;

    constructor(private wsService: WebSocketService) {
        this.subject =
            <Subject<SocketMessage>>this.wsService.connect(environment.WS_BASE_URL)
                .map((response: MessageEvent): SocketMessage => {
                    let data = JSON.parse(response.data);
                    return new SocketMessage(data.object, data.action);

                });


    }

    public send(o: SocketMessage) {
        this.subject.next(o);
    }


} 