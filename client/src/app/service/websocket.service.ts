import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { KeycloakService } from './keycloak.service';

@Injectable()
export class WebSocketService {
    private subject: Subject<MessageEvent>;

    constructor(private keycloakService: KeycloakService) {

    }

    public connect(url: string): Subject<MessageEvent> {
        if (!this.subject) {
            this.create(url).subscribe(subject => {
                this.subject = subject;
                console.log("merda");
            });
        }
        while (!this.subject) {
            setTimeout(() => {
                console.log("merda2");
            }, 150);
        }
        return this.subject;

    }

    private create(url: string): Observable<Subject<MessageEvent>> {

        const tokenPromise: Promise<string> = this.keycloakService.getToken();
        const tokenObservable: Observable<string> = Observable.fromPromise(tokenPromise);

        return tokenObservable.map(token => {
            return Observable.webSocket(url + "?access_token=" + token);
        });


    }

}