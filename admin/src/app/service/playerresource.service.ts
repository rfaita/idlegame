import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Battle } from '../model/battle';
import { PlayerResource } from '../model/playerResource';
import { StompService } from '@stomp/ng2-stompjs';
import { PlayerResourceStompConfig } from '../utils/playerStompConfigFactory';

@Injectable()
export class PlayerResourceService extends StompService {

    constructor(config: PlayerResourceStompConfig,
        private http: HttpClient) {
        super(config)

    }

    computeResources(): Observable<Envelope<PlayerResource>> {
        return <Observable<Envelope<PlayerResource>>>this.http.get(environment.API_BASE_URL + "playerResource/computeResources");
    }

    subscribeMonitor() {
        return this.state;
    }

    subscribeResourceRefresh(user: String): Observable<PlayerResource> {
        return this.subscribe("/queue/" + user + "#resource.refresh").map(message => JSON.parse(message.body));
    }


}



