import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Battle } from '../model/battle';
import { UserResource } from '../model/userResource';
import { StompService } from '@stomp/ng2-stompjs';
import { UserResourceStompConfig } from '../utils/playerStompConfigFactory';

@Injectable()
export class UserResourceService extends StompService {

    constructor(config: UserResourceStompConfig,
        private http: HttpClient) {
        super(config)

    }

    computeResources(): Observable<Envelope<UserResource>> {
        return <Observable<Envelope<UserResource>>>this.http.get(environment.API_BASE_URL + "userResource/computeResources");
    }

    subscribeMonitor() {
        return this.state;
    }

    subscribeResourceRefresh(user: String): Observable<UserResource> {
        return this.subscribe("/queue/" + user + "#resource.refresh").map(message => JSON.parse(message.body));
    }


}



