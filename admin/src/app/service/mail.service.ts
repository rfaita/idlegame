import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';
import { StompService } from '@stomp/ng2-stompjs';
import { Mail } from '../model/mail';
import { MailStompConfig } from '../utils/mailStompConfigFactory';



@Injectable()
export class MailService extends StompService {

    constructor(config: MailStompConfig, private http: HttpClient) {
        super(config);

    }

    subscribeMonitor() {
        return this.state;
    }

    subscribePrivateMail(user: String): Observable<Mail> {
        return this.subscribe("/queue/" + user + "#mail.private").map(message => JSON.parse(message.body));
    }

    subscribePrivateMailDelete(user: String): Observable<Mail> {
        return this.subscribe("/queue/" + user + "#mail.private.delete").map(message => JSON.parse(message.body));
    }

    subscribePrivateMailUpdate(user: String): Observable<Mail> {
        return this.subscribe("/queue/" + user + "#mail.private.update").map(message => JSON.parse(message.body));
    }


}



