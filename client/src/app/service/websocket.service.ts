import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { KeycloakService } from './keycloak.service';
import { SocketMessage } from '../model/socketMessage';
import { WebSocketSubject } from 'rxjs/observable/dom/WebSocketSubject';

@Injectable()
export class WebSocketService {
    public subject: WebSocketSubject<any>;

    constructor(private keycloakService: KeycloakService) {
    }

    public connect(url: string, authToken: string): WebSocketSubject<any> {
        if (!this.subject) {
            this.subject = this.create(url, authToken);
        }
        return this.subject;

    }

    private create(url: string, authToken: string): WebSocketSubject<any> {
        return Observable.webSocket(url + "?access_token=" + authToken);
    }

}